package be.lemonade.androidtalk.webservices;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.os.AsyncTask;

/**
 * Async task for HTTP calls
 * @author rafael
 *
 */
public abstract class HttpRequestAsyncTask extends AsyncTask<String, String, String>{

	/*
	 * (non-Javadoc)
	 * @see android.os.AsyncTask#doInBackground(Params[])
	 */
    @Override
    protected String doInBackground(String... uri) {
        HttpClient httpClient;
        HttpResponse response;
        String responseString;
        StatusLine statusLine;
        ByteArrayOutputStream out;
        
        //response string is initialized as null
        responseString = null;
        
        //default http client is created
        httpClient = new DefaultHttpClient();

        try {
        	
        	
            response = httpClient.execute(new HttpGet(uri[0]));
            
            statusLine = response.getStatusLine();
            
            if(statusLine.getStatusCode() == HttpStatus.SC_OK){
            	
                out = new ByteArrayOutputStream();
                
                response.getEntity().writeTo(out);
                
                out.close();
                
                responseString = out.toString();
            } 
            else{
            	
                //closes the connection.
                response.getEntity().getContent().close();
                throw new IOException(statusLine.getReasonPhrase());
            }
        } catch (ClientProtocolException e) {
        	e.printStackTrace();
        } catch (IOException e) {
        	e.printStackTrace();
        }
        
        return responseString;
    }

    /*
     * (non-Javadoc)
     * @see android.os.AsyncTask#onPostExecute(java.lang.Object)
     */
    @Override
    protected void onPostExecute(String response) {
        super.onPostExecute(response);
        
        //stuff is done with the response
        execute(response);
    }
    
    /**
     * Do stuff with the response
     * @param response
     */
    protected abstract void execute(String response);
}