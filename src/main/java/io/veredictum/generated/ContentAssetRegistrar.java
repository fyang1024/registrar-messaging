package io.veredictum.generated;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Future;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.EventValues;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.DynamicArray;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Bytes32;
import org.web3j.abi.datatypes.generated.Bytes6;
import org.web3j.abi.datatypes.generated.Uint8;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import rx.Observable;
import rx.functions.Func1;

/**
 * Auto generated code.<br>
 * <strong>Do not modify!</strong><br>
 * Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>, or {@link org.web3j.codegen.SolidityFunctionWrapperGenerator} to update.
 *
 * <p>Generated with web3j version 2.3.0.
 */
public final class ContentAssetRegistrar extends Contract {
    private static final String BINARY = "6060604052341561000f57600080fd5b5b60008054600160a060020a03191633600160a060020a03161790555b5b610b438061003c6000396000f300606060405236156100a15763ffffffff7c01000000000000000000000000000000000000000000000000000000006000350416632267b2e381146100a657806326ddef08146100d557806345b38809146101095780635c255c491461014857806383197ef01461017c578063b56ce31d14610191578063d77ef1da146101b6578063d7e0b6e314610228578063d94f05e41461024c578063ec67b2131461027d575b600080fd5b34156100b157600080fd5b6100b96102af565b604051600160a060020a03909116815260200160405180910390f35b34156100e057600080fd5b6100f5600160d060020a0319600435166102be565b604051901515815260200160405180910390f35b341561011457600080fd5b6101466024600480358281019290820135918135918201910135600160d060020a0319604435166064356084356102d3565b005b341561015357600080fd5b610146600160a060020a0360043516600160d060020a03196024351660ff604435166105d3565b005b341561018757600080fd5b61014661082f565b005b341561019c57600080fd5b610146600160d060020a03196004351660243561085d565b005b34156101c157600080fd5b6101e2600160a060020a0360043516600160d060020a03196024351661093f565b604051600160a060020a039095168552600160d060020a03199093166020850152604080850192909252606084015260ff909116608083015260a0909101905180910390f35b341561023357600080fd5b610146600160a060020a036004351660243561099a565b005b341561025757600080fd5b61026b600160a060020a0360043516610a1f565b60405190815260200160405180910390f35b341561028857600080fd5b61026b600160d060020a031960043516610a31565b60405190815260200160405180910390f35b600054600160a060020a031681565b60026020526000908152604090205460ff1681565b60008060006102e0610ae9565b600054600160a060020a0390811690331681146102fc57600080fd5b6103328c8c8080602002602001604051908101604052809392919081815260200183836020028082843750610a43945050505050565b151561033d57600080fd5b8a891461034957600080fd5b600160d060020a0319881660009081526002602052604090205460ff161561037057600080fd5b6103a68a8a8080602002602001604051908101604052809392919081815260200183836020028082843750610aa7945050505050565b15156103b157600080fd5b600094505b8a8510156104fe578989868181106103ca57fe5b9050602002013560ff1693508b8b8681811015156103e457fe5b90506020020135600160a060020a0316925060a06040519081016040908152600160a060020a038516808352600160d060020a03198b1660208085018290528385018c9052606085018b905260ff89166080860152600092835260018152838320918352522090925082908151815473ffffffffffffffffffffffffffffffffffffffff1916600160a060020a03919091161781556020820151815460d060020a909104740100000000000000000000000000000000000000000279ffffffffffff00000000000000000000000000000000000000001990911617815560408201516001820155606082015160028201556080820151600391909101805460ff191660ff909216919091179055505b6001909401936103b6565b600160d060020a0319881660009081526002602052604090819020805460ff191660011790557f1d5e8b1b94bf2fb36e01bb7a8548d95ef495bfe5caf8b76f1618ff1ecd5833ed908d908d908d908d908d908d908d9051600160d060020a031984166040820152606081018390526080810182905260a080825281018790528060208082019060c08301908b908b028082843790910184810383528881526020908101915089908902808284378201915050995050505050505050505060405180910390a15b5b505050505050505050505050565b600160a060020a033381166000908152600160209081526040808320600160d060020a03198716845290915290205416151561060e57600080fd5b600160a060020a0333166000908152600160209081526040808320600160d060020a03198616845290915290206003015460ff8083169116101561065157600080fd5b600160a060020a0333166000908152600160209081526040808320600160d060020a03198616845290915281206003015460ff161161068f57600080fd5b33600160a060020a039081166000908152600160208181526040808420600160d060020a031988168086529083528185206003908101805460ff1980821660ff9283168c90038316179092558b8916885295855283872092875291909352932090810180549384169383168601909216929092179055541615156107c457600160a060020a038381166000818152600160208181526040808420600160d060020a03198916808652818452828620805479ffffffffffff000000000000000000000000000000000000000019167401000000000000000000000000000000000000000060d060020a8d04021773ffffffffffffffffffffffffffffffffffffffff1916909717875533909716855283835281852096855295825290922080820154918401919091556002908101549390915201555b7fdaa9cffb77b1d392aa7651c0a2734da21f386792b1c777224ada67e05300ad4e33848484604051600160a060020a039485168152929093166020830152600160d060020a03191660408083019190915260ff9092166060820152608001905180910390a15b505050565b600054600160a060020a03908116903316811461084b57600080fd5b600054600160a060020a0316ff5b5b50565b600160d060020a0319821660009081526002602052604090205460ff16151561088557600080fd5b600160a060020a033381166000908152600160209081526040808320600160d060020a0319871684529091529020541615156108c057600080fd5b600160d060020a03198216600090815260036020526040908190208290557f4a88e4bb4fba842cd66f8be95108185bab06ef52b4ca603837cf7206ee9cbc069033908490849051600160a060020a039093168352600160d060020a031990911660208301526040808301919091526060909101905180910390a15b5050565b600160208181526000938452604080852090915291835291208054918101546002820154600390920154600160a060020a0384169374010000000000000000000000000000000000000000900460d060020a02929060ff1685565b600054600160a060020a0390811690331681146109b657600080fd5b600160a060020a038316600090815260046020526040908190208390557f4c5d15f5779ae1ecd7d55bd3283d898e29c35743d172bd7b83a16e17bc0afe2a908490849051600160a060020a03909216825260208201526040908101905180910390a15b5b505050565b60046020526000908152604090205481565b60036020526000908152604090205481565b6000805b8251811015610a9c5760046000848381518110610a6057fe5b90602001906020020151600160a060020a031681526020810191909152604001600020541515610a935760009150610aa1565b5b600101610a47565b600191505b50919050565b600080805b8351811015610adb57838181518110610ac157fe5b9060200190602002015160ff16820191505b600101610aac565b8160641492505b5050919050565b60a06040519081016040908152600080835260208301819052908201819052606082018190526080820152905600a165627a7a723058200f2cc54e5efed2346bfe7aad51ddc8fed513d89db08003cff5362546a64322000029";

    private ContentAssetRegistrar(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    private ContentAssetRegistrar(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public List<ContentRegisteredEventResponse> getContentRegisteredEvents(TransactionReceipt transactionReceipt) {
        final Event event = new Event("ContentRegistered", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Address>>() {}, new TypeReference<DynamicArray<Uint8>>() {}, new TypeReference<Bytes6>() {}, new TypeReference<Bytes32>() {}, new TypeReference<Bytes32>() {}));
        List<EventValues> valueList = extractEventParameters(event, transactionReceipt);
        ArrayList<ContentRegisteredEventResponse> responses = new ArrayList<ContentRegisteredEventResponse>(valueList.size());
        for (EventValues eventValues : valueList) {
            ContentRegisteredEventResponse typedResponse = new ContentRegisteredEventResponse();
            typedResponse._owners = (DynamicArray<Address>) eventValues.getNonIndexedValues().get(0);
            typedResponse._shares = (DynamicArray<Uint8>) eventValues.getNonIndexedValues().get(1);
            typedResponse._contentId = (Bytes6) eventValues.getNonIndexedValues().get(2);
            typedResponse._originalFileHash = (Bytes32) eventValues.getNonIndexedValues().get(3);
            typedResponse._transcodedFileHash = (Bytes32) eventValues.getNonIndexedValues().get(4);
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<ContentRegisteredEventResponse> contentRegisteredEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        final Event event = new Event("ContentRegistered", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Address>>() {}, new TypeReference<DynamicArray<Uint8>>() {}, new TypeReference<Bytes6>() {}, new TypeReference<Bytes32>() {}, new TypeReference<Bytes32>() {}));
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(event));
        return web3j.ethLogObservable(filter).map(new Func1<Log, ContentRegisteredEventResponse>() {
            @Override
            public ContentRegisteredEventResponse call(Log log) {
                EventValues eventValues = extractEventParameters(event, log);
                ContentRegisteredEventResponse typedResponse = new ContentRegisteredEventResponse();
                typedResponse._owners = (DynamicArray<Address>) eventValues.getNonIndexedValues().get(0);
                typedResponse._shares = (DynamicArray<Uint8>) eventValues.getNonIndexedValues().get(1);
                typedResponse._contentId = (Bytes6) eventValues.getNonIndexedValues().get(2);
                typedResponse._originalFileHash = (Bytes32) eventValues.getNonIndexedValues().get(3);
                typedResponse._transcodedFileHash = (Bytes32) eventValues.getNonIndexedValues().get(4);
                return typedResponse;
            }
        });
    }

    public List<OwnershipTransferredEventResponse> getOwnershipTransferredEvents(TransactionReceipt transactionReceipt) {
        final Event event = new Event("OwnershipTransferred", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Address>() {}, new TypeReference<Bytes6>() {}, new TypeReference<Uint8>() {}));
        List<EventValues> valueList = extractEventParameters(event, transactionReceipt);
        ArrayList<OwnershipTransferredEventResponse> responses = new ArrayList<OwnershipTransferredEventResponse>(valueList.size());
        for (EventValues eventValues : valueList) {
            OwnershipTransferredEventResponse typedResponse = new OwnershipTransferredEventResponse();
            typedResponse.from = (Address) eventValues.getNonIndexedValues().get(0);
            typedResponse.to = (Address) eventValues.getNonIndexedValues().get(1);
            typedResponse.contentId = (Bytes6) eventValues.getNonIndexedValues().get(2);
            typedResponse.share = (Uint8) eventValues.getNonIndexedValues().get(3);
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<OwnershipTransferredEventResponse> ownershipTransferredEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        final Event event = new Event("OwnershipTransferred", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Address>() {}, new TypeReference<Bytes6>() {}, new TypeReference<Uint8>() {}));
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(event));
        return web3j.ethLogObservable(filter).map(new Func1<Log, OwnershipTransferredEventResponse>() {
            @Override
            public OwnershipTransferredEventResponse call(Log log) {
                EventValues eventValues = extractEventParameters(event, log);
                OwnershipTransferredEventResponse typedResponse = new OwnershipTransferredEventResponse();
                typedResponse.from = (Address) eventValues.getNonIndexedValues().get(0);
                typedResponse.to = (Address) eventValues.getNonIndexedValues().get(1);
                typedResponse.contentId = (Bytes6) eventValues.getNonIndexedValues().get(2);
                typedResponse.share = (Uint8) eventValues.getNonIndexedValues().get(3);
                return typedResponse;
            }
        });
    }

    public List<UserRegisteredEventResponse> getUserRegisteredEvents(TransactionReceipt transactionReceipt) {
        final Event event = new Event("UserRegistered", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Bytes32>() {}));
        List<EventValues> valueList = extractEventParameters(event, transactionReceipt);
        ArrayList<UserRegisteredEventResponse> responses = new ArrayList<UserRegisteredEventResponse>(valueList.size());
        for (EventValues eventValues : valueList) {
            UserRegisteredEventResponse typedResponse = new UserRegisteredEventResponse();
            typedResponse.userAddress = (Address) eventValues.getNonIndexedValues().get(0);
            typedResponse.userInfoHash = (Bytes32) eventValues.getNonIndexedValues().get(1);
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<UserRegisteredEventResponse> userRegisteredEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        final Event event = new Event("UserRegistered", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Bytes32>() {}));
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(event));
        return web3j.ethLogObservable(filter).map(new Func1<Log, UserRegisteredEventResponse>() {
            @Override
            public UserRegisteredEventResponse call(Log log) {
                EventValues eventValues = extractEventParameters(event, log);
                UserRegisteredEventResponse typedResponse = new UserRegisteredEventResponse();
                typedResponse.userAddress = (Address) eventValues.getNonIndexedValues().get(0);
                typedResponse.userInfoHash = (Bytes32) eventValues.getNonIndexedValues().get(1);
                return typedResponse;
            }
        });
    }

    public List<AuthorisedDomainsRegisteredEventResponse> getAuthorisedDomainsRegisteredEvents(TransactionReceipt transactionReceipt) {
        final Event event = new Event("AuthorisedDomainsRegistered", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Bytes6>() {}, new TypeReference<Bytes32>() {}));
        List<EventValues> valueList = extractEventParameters(event, transactionReceipt);
        ArrayList<AuthorisedDomainsRegisteredEventResponse> responses = new ArrayList<AuthorisedDomainsRegisteredEventResponse>(valueList.size());
        for (EventValues eventValues : valueList) {
            AuthorisedDomainsRegisteredEventResponse typedResponse = new AuthorisedDomainsRegisteredEventResponse();
            typedResponse.owner = (Address) eventValues.getNonIndexedValues().get(0);
            typedResponse.contentId = (Bytes6) eventValues.getNonIndexedValues().get(1);
            typedResponse.authorisedDomainsHash = (Bytes32) eventValues.getNonIndexedValues().get(2);
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<AuthorisedDomainsRegisteredEventResponse> authorisedDomainsRegisteredEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        final Event event = new Event("AuthorisedDomainsRegistered", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Bytes6>() {}, new TypeReference<Bytes32>() {}));
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(event));
        return web3j.ethLogObservable(filter).map(new Func1<Log, AuthorisedDomainsRegisteredEventResponse>() {
            @Override
            public AuthorisedDomainsRegisteredEventResponse call(Log log) {
                EventValues eventValues = extractEventParameters(event, log);
                AuthorisedDomainsRegisteredEventResponse typedResponse = new AuthorisedDomainsRegisteredEventResponse();
                typedResponse.owner = (Address) eventValues.getNonIndexedValues().get(0);
                typedResponse.contentId = (Bytes6) eventValues.getNonIndexedValues().get(1);
                typedResponse.authorisedDomainsHash = (Bytes32) eventValues.getNonIndexedValues().get(2);
                return typedResponse;
            }
        });
    }

    public Future<Address> veredictum() {
        Function function = new Function("veredictum", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<Bool> contentIdRegistered(Bytes6 param0) {
        Function function = new Function("contentIdRegistered", 
                Arrays.<Type>asList(param0), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<TransactionReceipt> registerContent(DynamicArray<Address> _owners, DynamicArray<Uint8> _shares, Bytes6 _contentId, Bytes32 _originalFileHash, Bytes32 _transcodedFileHash) {
        Function function = new Function("registerContent", Arrays.<Type>asList(_owners, _shares, _contentId, _originalFileHash, _transcodedFileHash), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public Future<TransactionReceipt> transfer(Address _other, Bytes6 _contentId, Uint8 _share) {
        Function function = new Function("transfer", Arrays.<Type>asList(_other, _contentId, _share), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public Future<TransactionReceipt> destroy() {
        Function function = new Function("destroy", Arrays.<Type>asList(), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public Future<TransactionReceipt> registerAuthorisedDomains(Bytes6 _contentId, Bytes32 _authorisedDomainsHash) {
        Function function = new Function("registerAuthorisedDomains", Arrays.<Type>asList(_contentId, _authorisedDomainsHash), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public Future<List<Type>> contentIdRegistrar(Address param0, Bytes6 param1) {
        Function function = new Function("contentIdRegistrar", 
                Arrays.<Type>asList(param0, param1), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Bytes6>() {}, new TypeReference<Bytes32>() {}, new TypeReference<Bytes32>() {}, new TypeReference<Uint8>() {}));
        return executeCallMultipleValueReturnAsync(function);
    }

    public Future<TransactionReceipt> registerUser(Address _userAddress, Bytes32 _userInfoHash) {
        Function function = new Function("registerUser", Arrays.<Type>asList(_userAddress, _userInfoHash), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public Future<Bytes32> userInfoHashRegistrar(Address param0) {
        Function function = new Function("userInfoHashRegistrar", 
                Arrays.<Type>asList(param0), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<Bytes32> authorisedDomainsRegistrar(Bytes6 param0) {
        Function function = new Function("authorisedDomainsRegistrar", 
                Arrays.<Type>asList(param0), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public static Future<ContentAssetRegistrar> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, BigInteger initialWeiValue) {
        return deployAsync(ContentAssetRegistrar.class, web3j, credentials, gasPrice, gasLimit, BINARY, "", initialWeiValue);
    }

    public static Future<ContentAssetRegistrar> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, BigInteger initialWeiValue) {
        return deployAsync(ContentAssetRegistrar.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "", initialWeiValue);
    }

    public static ContentAssetRegistrar load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new ContentAssetRegistrar(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public static ContentAssetRegistrar load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new ContentAssetRegistrar(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static class ContentRegisteredEventResponse {
        public DynamicArray<Address> _owners;

        public DynamicArray<Uint8> _shares;

        public Bytes6 _contentId;

        public Bytes32 _originalFileHash;

        public Bytes32 _transcodedFileHash;
    }

    public static class OwnershipTransferredEventResponse {
        public Address from;

        public Address to;

        public Bytes6 contentId;

        public Uint8 share;
    }

    public static class UserRegisteredEventResponse {
        public Address userAddress;

        public Bytes32 userInfoHash;
    }

    public static class AuthorisedDomainsRegisteredEventResponse {
        public Address owner;

        public Bytes6 contentId;

        public Bytes32 authorisedDomainsHash;
    }
}
