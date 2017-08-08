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
    private static final String BINARY = "6060604052341561000f57600080fd5b5b60008054600160a060020a03191633600160a060020a03161790555b5b61101d8061003c6000396000f300606060405236156100805763ffffffff7c010000000000000000000000000000000000000000000000000000000060003504166305f33eee81146100855780632267b2e3146100f657806326ddef081461012557806345b38809146101595780635c255c491461019857806383197ef0146101cc578063d77ef1da146101e1575b600080fd5b341561009057600080fd5b6100a7600160a060020a036004351660243561025c565b604051600160a060020a039096168652600160d060020a03199094166020860152604080860193909352606085019190915260ff16608084015290151560a083015260c0909101905180910390f35b341561010157600080fd5b6101096102b0565b604051600160a060020a03909116815260200160405180910390f35b341561013057600080fd5b610145600160d060020a0319600435166102bf565b604051901515815260200160405180910390f35b341561016457600080fd5b6101966024600480358281019290820135918135918201910135600160d060020a0319604435166064356084356102d4565b005b34156101a357600080fd5b610196600160a060020a0360043516600160d060020a03196024351660ff60443516610754565b005b34156101d757600080fd5b610196610ef9565b005b34156101ec57600080fd5b6100a7600160a060020a0360043516600160d060020a031960243516610f27565b604051600160a060020a039096168652600160d060020a03199094166020860152604080860193909352606085019190915260ff16608084015290151560a083015260c0909101905180910390f35b600260208181526000938452604080852090915291835291208054600182015492820154600390920154600160a060020a0382169360a060020a90920460d060020a02929060ff8082169161010090041686565b600054600160a060020a031681565b60036020526000908152604090205460ff1681565b60008060006102e1610fbc565b600054600160a060020a0390811690331681146102fd57600080fd5b8a891461030957600080fd5b600160d060020a0319881660009081526003602052604090205460ff161561033057600080fd5b6103668a8a8080602002602001604051908101604052809392919081815260200183836020028082843750610f7a945050505050565b151561037157600080fd5b600094505b8a85101561067f5789898681811061038a57fe5b9050602002013560ff1693508b8b8681811015156103a457fe5b90506020020135600160a060020a0316925060c06040519081016040908152600160a060020a038516808352600160d060020a03198b1660208085018290528385018c9052606085018b905260ff89166080860152600160a0860181905260009384528152838320918352522090925082908151815473ffffffffffffffffffffffffffffffffffffffff1916600160a060020a03919091161781556020820151815460d060020a90910460a060020a0279ffffffffffff0000000000000000000000000000000000000000199091161781556040820151600182015560608201516002820155608082015160038201805460ff191660ff9290921691909117905560a0820151600390910180549115156101000261ff001990921691909117905550600160a060020a03831660009081526002602090815260408083208a8452909152902082908151815473ffffffffffffffffffffffffffffffffffffffff1916600160a060020a03919091161781556020820151815460d060020a90910460a060020a0279ffffffffffff0000000000000000000000000000000000000000199091161781556040820151600182015560608201516002820155608082015160038201805460ff191660ff9290921691909117905560a0820151600390910180549115156101000261ff001990921691909117905550600160a060020a0383166000908152600260209081526040808320898452909152902082908151815473ffffffffffffffffffffffffffffffffffffffff1916600160a060020a03919091161781556020820151815460d060020a90910460a060020a0279ffffffffffff0000000000000000000000000000000000000000199091161781556040820151600182015560608201516002820155608082015160038201805460ff191660ff9290921691909117905560a0820151600390910180549115156101000261ff0019909216919091179055505b600190940193610376565b600160d060020a0319881660009081526003602052604090819020805460ff191660011790557f1d5e8b1b94bf2fb36e01bb7a8548d95ef495bfe5caf8b76f1618ff1ecd5833ed908d908d908d908d908d908d908d9051600160d060020a031984166040820152606081018390526080810182905260a080825281018790528060208082019060c08301908b908b028082843790910184810383528881526020908101915089908902808284378201915050995050505050505050505060405180910390a15b5b505050505050505050505050565b33600160a060020a03166000908152600160209081526040808320600160d060020a0319861684529091528120600301548190610100900460ff16151561079a57600080fd5b600160a060020a0333166000908152600160209081526040808320600160d060020a03198816845290915290206003015460ff808516911610156107dd57600080fd5b600160a060020a0333166000908152600160209081526040808320600160d060020a03198816845290915281206003015460ff161161081b57600080fd5b600160a060020a033381166000908152600160208181526040808420600160d060020a03198a168086529083528185206003908101805460ff8082168d9003811660ff1992831617909255978d168752948452828620918652925290922090910180548083168701831693169290921791829055610100909104161515610e8c576001600033600160a060020a0316600160a060020a03168152602001908152602001600020600085600160d060020a031916600160d060020a031916815260200190815260200160002060000160149054906101000a900460d060020a026001600087600160a060020a0316600160a060020a03168152602001908152602001600020600086600160d060020a031916600160d060020a031916815260200190815260200160002060000160146101000a81548165ffffffffffff021916908360d060020a900402179055506001600033600160a060020a0316600160a060020a03168152602001908152602001600020600085600160d060020a031916600160d060020a0319168152602001908152602001600020600101549150816001600087600160a060020a0316600160a060020a03168152602001908152602001600020600086600160d060020a031916600160d060020a031916815260200190815260200160002060010181600019169055506001600033600160a060020a0316600160a060020a03168152602001908152602001600020600085600160d060020a031916600160d060020a0319168152602001908152602001600020600201549050806001600087600160a060020a0316600160a060020a03168152602001908152602001600020600086600160d060020a031916600160d060020a03191681526020019081526020016000206002018160001916905550846001600087600160a060020a0316600160a060020a03168152602001908152602001600020600086600160d060020a031916600160d060020a031916815260200190815260200160002060000160006101000a815481600160a060020a030219169083600160a060020a03160217905550600180600087600160a060020a0316600160a060020a03168152602001908152602001600020600086600160d060020a031916600160d060020a031916815260200190815260200160002060030160016101000a81548160ff0219169083151502179055506001600086600160a060020a0316600160a060020a03168152602001908152602001600020600085600160d060020a031916600160d060020a03191681526020019081526020016000206002600087600160a060020a0316600160a060020a03168152602001908152602001600020600084600019166000191681526020019081526020016000206000820160009054906101000a9004600160a060020a03168160000160006101000a815481600160a060020a030219169083600160a060020a031602179055506000820160149054906101000a900460d060020a028160000160146101000a81548165ffffffffffff021916908360d060020a9004021790555060018201548160010190600019169055600282015481600201906000191690556003820160009054906101000a900460ff168160030160006101000a81548160ff021916908360ff1602179055506003820160019054906101000a900460ff168160030160016101000a81548160ff0219169083151502179055509050506001600086600160a060020a0316600160a060020a03168152602001908152602001600020600085600160d060020a031916600160d060020a03191681526020019081526020016000206002600087600160a060020a0316600160a060020a03168152602001908152602001600020600083600019166000191681526020019081526020016000206000820160009054906101000a9004600160a060020a03168160000160006101000a815481600160a060020a030219169083600160a060020a031602179055506000820160149054906101000a900460d060020a028160000160146101000a81548165ffffffffffff021916908360d060020a9004021790555060018201548160010190600019169055600282015481600201906000191690556003820160009054906101000a900460ff168160030160006101000a81548160ff021916908360ff1602179055506003820160019054906101000a900460ff168160030160016101000a81548160ff0219169083151502179055509050505b7fdaa9cffb77b1d392aa7651c0a2734da21f386792b1c777224ada67e05300ad4e33868686604051600160a060020a039485168152929093166020830152600160d060020a03191660408083019190915260ff9092166060820152608001905180910390a15b5050505050565b600054600160a060020a039081169033168114610f1557600080fd5b600054600160a060020a0316ff5b5b50565b600160208181526000938452604080852090915291835291208054918101546002820154600390920154600160a060020a0384169360a060020a900460d060020a02929060ff8082169161010090041686565b600080805b8351811015610fae57838181518110610f9457fe5b9060200190602002015160ff16820191505b600101610f7f565b8160641492505b5050919050565b60c06040519081016040908152600080835260208301819052908201819052606082018190526080820181905260a0820152905600a165627a7a72305820a622a99038f1295d401602333dd3921f463f475c17b78280960388940e7b233f0029";

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

    public Future<List<Type>> contentHashRegistrar(Address param0, Bytes32 param1) {
        Function function = new Function("contentHashRegistrar", 
                Arrays.<Type>asList(param0, param1), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Bytes6>() {}, new TypeReference<Bytes32>() {}, new TypeReference<Bytes32>() {}, new TypeReference<Uint8>() {}, new TypeReference<Bool>() {}));
        return executeCallMultipleValueReturnAsync(function);
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

    public Future<List<Type>> contentIdRegistrar(Address param0, Bytes6 param1) {
        Function function = new Function("contentIdRegistrar", 
                Arrays.<Type>asList(param0, param1), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Bytes6>() {}, new TypeReference<Bytes32>() {}, new TypeReference<Bytes32>() {}, new TypeReference<Uint8>() {}, new TypeReference<Bool>() {}));
        return executeCallMultipleValueReturnAsync(function);
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
}
