/*
TODO add license here
*/
pragma solidity ^0.4.14;

/**
@title Content Asset Registrar
Content Asset Registrar maintained by Veredictum and content owners

@authors:
    Fei Yang <fei.yang@veredictum.io>
    Samuel Brooks <sam.brooks@veredictum.io>
*/
contract ContentAssetRegistrar {

    struct ContentOwnership {
        address owner;
        bytes6 contentId; // embedded in the content by Veredictum
        bytes32 originalFileHash;
        bytes32 transcodedFileHash;
        uint8 share; // percentage without %
    }

    // make it public so that people can verify it against official address published by Veredictum
    address public veredictum;

    // make them public so that 3rd party can access the data (read-only)
    mapping (address => mapping (bytes6 => ContentOwnership)) public contentIdRegistrar;
    mapping (bytes6 => bool) public contentIdRegistered;
    mapping (bytes6 => bytes32) public authorisedDomainsRegistrar; // mapping from contentId to hash of authorisedDomains
    mapping (address => bytes32) public userInfoHashRegistrar;

    event ContentRegistered (
        address[] _owners,
        uint8[] _shares,
        bytes6 _contentId,
        bytes32 _originalFileHash,
        bytes32 _transcodedFileHash
    );

    event OwnershipTransferred (
        address from,
        address to,
        bytes6 contentId,
        uint8 share
    );

    event UserRegistered (
        address userAddress,
        bytes32 userInfoHash
    );

    event AuthorisedDomainsRegistered (
        address owner,
        bytes6 contentId,
        bytes32 authorisedDomainsHash
    );

    modifier onlyBy(address _account) {
        require(msg.sender == _account);
        _;
    }

    // constructor
    function ContentAssetRegistrar() {
        veredictum = msg.sender;
    }

    // for Veredictum to register content ownership
    function registerContent(
        address[] _owners,
        uint8[] _shares,
        bytes6 _contentId,
        bytes32 _originalFileHash,
        bytes32 _transcodedFileHash
    )
        external
        onlyBy(veredictum)
    {
        require(ownersRegistered(_owners));
        require(_owners.length == _shares.length);
        require(!contentIdRegistered[_contentId]);
        require(checkSum(_shares));

        for (uint i = 0; i < _owners.length; i++) {
            uint8 share = _shares[i];
            address owner = _owners[i];
            ContentOwnership memory ownership = ContentOwnership(
                owner,
                _contentId,
                _originalFileHash,
                _transcodedFileHash,
                share
            );
            contentIdRegistrar[owner][_contentId] = ownership;
        }
        contentIdRegistered[_contentId] = true;

        ContentRegistered(_owners, _shares, _contentId, _originalFileHash, _transcodedFileHash);
    }

    // for Veredictum to register user info hash
    function registerUser(address _userAddress, bytes32 _userInfoHash) external onlyBy(veredictum) {
        userInfoHashRegistrar[_userAddress] = _userInfoHash;
        UserRegistered(_userAddress, _userInfoHash);
    }

    // for content owner to transfer ownership to other person
    function transfer(address _other, bytes6 _contentId, uint8 _share) external {
        require(contentIdRegistrar[msg.sender][_contentId].owner != 0);
        require(contentIdRegistrar[msg.sender][_contentId].share >= _share);
        require(contentIdRegistrar[msg.sender][_contentId].share > 0);

        contentIdRegistrar[msg.sender][_contentId].share -= _share;
        contentIdRegistrar[_other][_contentId].share += _share;
        if (contentIdRegistrar[_other][_contentId].owner == 0) {
            contentIdRegistrar[_other][_contentId].contentId = _contentId;
            contentIdRegistrar[_other][_contentId].owner = _other;
            contentIdRegistrar[_other][_contentId].originalFileHash = contentIdRegistrar[msg.sender][_contentId].originalFileHash;
            contentIdRegistrar[_other][_contentId].transcodedFileHash = contentIdRegistrar[msg.sender][_contentId].transcodedFileHash;
        }

        OwnershipTransferred (msg.sender, _other, _contentId, _share);
    }

    // for content owner to register authorised domains hash for specific content
    function registerAuthorisedDomains(bytes6 _contentId, bytes32 _authorisedDomainsHash) external {
        require(contentIdRegistered[_contentId]);
        require(contentIdRegistrar[msg.sender][_contentId].owner != 0);

        authorisedDomainsRegistrar[_contentId] = _authorisedDomainsHash;

        AuthorisedDomainsRegistered(msg.sender, _contentId, _authorisedDomainsHash);
    }

    function destroy() external onlyBy(veredictum) {
        selfdestruct(veredictum);
    }

    // check if all _owners are registered users
    function ownersRegistered(address[] _owners) internal constant returns (bool) {
        for (uint i = 0; i < _owners.length; i++) {
            if (userInfoHashRegistrar[_owners[i]] == 0) {
                return false;
            }
        }
        return true;
    }

    // check if sum of _shares is 100
    function checkSum(uint8[] _shares) internal constant returns (bool) {
        uint sum = 0;
        for (uint i = 0; i < _shares.length; i++) {
            sum += _shares[i];
        }
        return sum == 100;
    }
}