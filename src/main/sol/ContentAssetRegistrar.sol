pragma solidity ^0.4.13;


contract ContentAssetRegistrar {

    struct ContentOwnership {
        address owner;
        bytes6 contentId; // max 42 bit, that is less than 6 bytes
        bytes32 originalFileHash;
        bytes32 transcodedFileHash;
        uint8 share; // percentage without %
        bool valid;
    }

    address public veredictum;

    mapping (address => mapping (bytes6 => ContentOwnership)) public contentIdRegistrar;
    mapping (address => mapping (bytes32 => ContentOwnership)) public contentHashRegistrar;
    mapping (bytes6 => bool) public contentIdRegistered;

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

    modifier onlyBy(address _account) {
        require(msg.sender == _account);
        _;
    }

    function ContentAssetRegistrar() {
        veredictum = msg.sender;
    }

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
                share,
                true
            );

            contentIdRegistrar[owner][_contentId] = ownership;
            contentHashRegistrar[owner][_originalFileHash] = ownership;
            contentHashRegistrar[owner][_transcodedFileHash] = ownership;
        }
        contentIdRegistered[_contentId] = true;

        ContentRegistered(_owners, _shares, _contentId, _originalFileHash, _transcodedFileHash);
    }

    function transfer(address _other, bytes6 _contentId, uint8 _share) external {
        require(contentIdRegistrar[msg.sender][_contentId].valid);
        require(contentIdRegistrar[msg.sender][_contentId].share >= _share);
        require(contentIdRegistrar[msg.sender][_contentId].share > 0);

        contentIdRegistrar[msg.sender][_contentId].share -= _share;
        contentIdRegistrar[_other][_contentId].share += _share;
        if (!contentIdRegistrar[_other][_contentId].valid) {
            contentIdRegistrar[_other][_contentId].contentId = contentIdRegistrar[msg.sender][_contentId].contentId;
            bytes32 originalFileHash = contentIdRegistrar[msg.sender][_contentId].originalFileHash;
            contentIdRegistrar[_other][_contentId].originalFileHash = originalFileHash;
            bytes32 transcodedFileHash = contentIdRegistrar[msg.sender][_contentId].transcodedFileHash;
            contentIdRegistrar[_other][_contentId].transcodedFileHash = transcodedFileHash;
            contentIdRegistrar[_other][_contentId].owner = _other;
            contentIdRegistrar[_other][_contentId].valid = true;
            contentHashRegistrar[_other][originalFileHash] = contentIdRegistrar[_other][_contentId];
            contentHashRegistrar[_other][transcodedFileHash] = contentIdRegistrar[_other][_contentId];
        }

        OwnershipTransferred (msg.sender, _other, _contentId, _share);
    }

    function destroy() external onlyBy(veredictum) {
        selfdestruct(veredictum);
    }

    function checkSum(uint8[] _shares) internal returns (bool) {
        uint sum = 0;
        for (uint i = 0; i < _shares.length; i++) {
            sum += _shares[i];
        }
        return sum == 100;
    }
}
