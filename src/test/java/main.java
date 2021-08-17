import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.Request;
import org.web3j.protocol.core.methods.response.EthBlock;
import org.web3j.protocol.http.HttpService;
import org.web3j.protocol.core.methods.response.EthBlockNumber;
import java.lang.String;
import java.lang.*;
import java.util.concurrent.ExecutionException;

public class main {
    public static void main(String[] args) throws ExecutionException, InterruptedException{
        Web3j web3 = Web3j.build(new HttpService("https://main-light.eth.linkpool.io"));
        EthBlockNumber result = web3.ethBlockNumber().sendAsync().get();
        System.out.println(" The Block Number is: " + result.getBlockNumber().toString());
        System.out.println(getBlockByNumber(59).getId());
    }

    public static Request<?, EthBlock> getBlockByNumber(int number){
        Web3j web3 = Web3j.build(new HttpService("https://main-light.eth.linkpool.io"));
        return web3.ethGetBlockByNumber(new DefaultBlockParameter() {
            @Override
            public String getValue() {
                return number+"";
            }
        },true);
    }
}
