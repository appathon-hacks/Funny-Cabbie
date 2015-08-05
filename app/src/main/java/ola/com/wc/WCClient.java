package ola.com.wc;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

/**
 * Created by Maharshi on 08-02-2015.
 */
public class WCClient {

    private static final String BASE_URL = "http://wcapp.herokuapp.com";
    private static final String COMMAND_URL = "/command";

    private static AsyncHttpClient client = new AsyncHttpClient();

    public static void command(String command,String[] args,JsonHttpResponseHandler jsonHandler){
        RequestParams params = new RequestParams();
        params.put("args",getCsv(args));
        get(COMMAND_URL+'/'+command,params,jsonHandler);
    }


    public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.get(getAbsoluteUrl(url), params, responseHandler);
    }

    public static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.post(getAbsoluteUrl(url), params, responseHandler);
    }

    private static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }

    private static String getCsv(String[] args){
        if (args.length > 0) {
            StringBuilder nameBuilder = new StringBuilder();

            for (String n : args) {
                nameBuilder.append("'").append(n.replace("'", "\\'")).append("',");
            }

            nameBuilder.deleteCharAt(nameBuilder.length() - 1);

            return nameBuilder.toString();
        } else {
            return "";
        }
    }
}
