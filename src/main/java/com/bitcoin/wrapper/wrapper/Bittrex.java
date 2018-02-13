package com.bitcoin.wrapper.wrapper;

import com.bitcoin.wrapper.bittrexJson2JavaObjects.publicApiObjects.*;
import com.bitcoin.wrapper.bittrexJson2JavaObjects.publicApiObjects.Currency; //avoid abiguity with java.util.Currency

import com.google.gson.Gson;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.MalformedURLException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;


import java.util.*;

public class Bittrex {
//    private String userAPIKey;
//    private String userSecret;
//    private static final String BASE_URL = "https://bittrex.com/api/v1.1/";
//
//    private HashMap<String, Market> markets = new HashMap<String, Market>();
//    private HashMap<String, Double> txFees = new HashMap<String, Double>();
//    private List<MarketSummary> marketStates = null;
//
//    public Bittrex() {
//        this("8c026ba8b794411884b4b6610c0f38d8", "SMQ6JCD32LE2NMQV");
//    }
//
//    public Bittrex(String apiKey, String secret){
//        this.userAPIKey = apiKey;
//        this.userSecret = secret;
//        initializeMarkets();
//    }
//
//
//    ///// PUBLIC API /////
//
//    public String getMarkets() {
//        return getJSONFromBittrex(BASE_URL +"public/getmarkets/");
//    }
//
//    public String getCurrencies() {
//        return getJSONFromBittrex(BASE_URL + "public/getcurrencies");
//    }
//
//    public String getTicker(String market) {
//        if (checkMarket(market)) {
//            return getJSONFromBittrex(BASE_URL + "public/getticker?market=" + market);
//        } else {
//            return null;
//        }
//    }
//
//    public String getMarketSummaries() {
//        return  getJSONFromBittrex(BASE_URL + "public/getmarketsummaries");
//    }
//
//    public String getOrderBook(String market) {
//        return this.getOrderBook(market, "both", 50);
//    }
//
//    public String getOrderBook(String market, String type) {
//        return this.getOrderBook(market, type, 50);
//    }
//
//    public String getOrderBook(String market, int depth) {
//        return this.getOrderBook(market, "both", depth);
//    }
//
//    public String getOrderBook(String market, String type, int depth) {
//        if (checkMarket(market)) {
//            if (!(type.equalsIgnoreCase("both") || type.equalsIgnoreCase("buy") || type.equalsIgnoreCase("sell"))) {
//                //invalid type;
//                return null;
//            } else if (depth < 1 || depth > 50) {
//                //invalid depth;
//                return null;
//            }
//            return getJSONFromBittrex(BASE_URL + "public/getorderbook?market=" + market + "&type=" + type + "&depth=" + Integer.toString(depth));
//        } else {
//            return null;
//        }
//    }
//
//    public String getMarketSummary(String market) {
//        if (checkMarket(market)) {
//            return getJSONFromBittrex(BASE_URL + "public/getmarketsummary?market=" + market);
//        } else {
//            return null;
//        }
//    }
//
//    public String getMarketHistory(String market) {
//        if (checkMarket(market)) {
//            return getJSONFromBittrex(BASE_URL + "public/getmarkethistory?market=" + market);
//        } else {
//            return null;
//        }
//    }
//
//    ///// MARKET API /////
//
//    public String buyLimit(String market, Double quanity, Double rate) {
//        Market marketObj = getMarket(market);
//        Double minTradeSize = marketObj.getMinTradeSize();
//        if (checkMarket(market) && quanity > minTradeSize && rate > 0) {
//            String url = BASE_URL + "market/buylimit?apikey=" + this.userAPIKey + "&market=" + market
//                + "&quantity=" + quanity + "&rate=" + rate + "&nonce=" + getNonce();
//            String apiSig = getHmacSHA512(url, this.userSecret);
//            return getJSONFromBittrex(url, apiSig);
//        } else {
//            return null;
//        }
//    }
//
//    public String sellLimit(String market, Double quanity, Double rate) {
//        Market marketObj = getMarket(market);
//        Double minTradeSize = marketObj.getMinTradeSize();
//        if (checkMarket(market) && quanity > minTradeSize && rate > 0) {
//            String url = BASE_URL + "market/selllimit?apikey=" + this.userAPIKey + "&market=" + market
//                    + "&quantity=" + quanity + "&rate=" + rate + "&nonce=" + getNonce();
//            String apiSig = getHmacSHA512(url, this.userSecret);
//            return getJSONFromBittrex(url, apiSig);
//        } else {
//            return null;
//        }
//    }
//
//    public String cancel(String uuid) {
//        String url = BASE_URL + "market/cancel?apikey=" + this.userAPIKey + "&uuid=" + uuid + "&nonce=" + getNonce();
//        String apiSig = getHmacSHA512(url, this.userSecret);
//        return getJSONFromBittrex(url, apiSig);
//    }
//
//    public String getOpenOrders() {
//        String url = BASE_URL + "market/getopenorders?apikey=" + this.userAPIKey + "&nonce=" + getNonce();
//        String apiSig = getHmacSHA512(url, this.userSecret);
//        return getJSONFromBittrex(url, apiSig);
//    }
//
//    public String getOpenOrders(String market) {
//        if (checkMarket(market)) {
//            String url = BASE_URL + "market/getopenorders?apikey=" + this.userAPIKey + "&market=" + market + "&nonce=" + getNonce();
//            String apiSig = getHmacSHA512(url, this.userSecret);
//            return getJSONFromBittrex(url, apiSig);
//        } else {
//            return null;
//        }
//    }
//
//    ///// ACCOUNT API /////
//
//    public String getBalances() {
//        if (apiFieldsEmpty()) {
//            return null;
//        } else {
//            String url = BASE_URL + "account/getbalances?apikey=" + this.userAPIKey + "&nonce=" + getNonce();
//            String apiSig = getHmacSHA512(url, this.userSecret);
//            return getJSONFromBittrex(url, apiSig);
//        }
//    }
//
//    public String getBalance(String currency) {
//        if (apiFieldsEmpty() || !checkCurrency(currency)) {
//            return null;
//        } else {
//            String url = BASE_URL + "account/getbalance?apikey=" + this.userAPIKey + "&currency=" + currency + "&nonce=" + getNonce();
//            String apiSig = getHmacSHA512(url, this.userSecret);
//            return getJSONFromBittrex(url, apiSig);
//        }
//    }
//
//    public String getDepositAddress(String currency) {
//        if (apiFieldsEmpty() || !checkCurrency(currency)) {
//            return null;
//        } else {
//            String url = BASE_URL + "account/getdepositaddress?apikey=" + this.userAPIKey + "&currency=" + currency + "&nonce=" + getNonce();
//            String apiSig = getHmacSHA512(url, this.userSecret);
//            return getJSONFromBittrex(url, apiSig);
//        }
//    }
//
//
//    public String withdraw(String currency, Double quantity, String address) {
//        return withdraw(currency, quantity, address, "");
//    }
//
//    public String withdraw(String currency, Double quantity, String address, String paymentId) {
//        if (apiFieldsEmpty() || !checkCurrency(currency)) {
//            return null;
//        } else {
//            String url = BASE_URL + "account/withdraw?apikey=" + this.userAPIKey + "&currency=" + currency +
//                    "&quantity=" + Double.toString(quantity) + "&address=" + address;
//            if (!paymentId.isEmpty()) {
//                url = url + "&paymentid=" + paymentId;
//            }
//            url = url + "&nonce=" + getNonce();
//            String apiSig = getHmacSHA512(url, this.userSecret);
//            return getJSONFromBittrex(url, apiSig);
//        }
//    }
//
//    public String getOrder(String uuid) {
//        if (apiFieldsEmpty() || uuid.isEmpty()) {
//            return null;
//        } else {
//            String url = BASE_URL + "account/getorder?apikey=" + this.userAPIKey + "&uuid=" + uuid + "&nonce=" + getNonce();
//            String apiSig = getHmacSHA512(url, this.userSecret);
//            return getJSONFromBittrex(url, apiSig);
//        }
//    }
//
//    public String getOrderHistory() {
//        if (apiFieldsEmpty()) {
//            return null;
//        } else {
//            String url = BASE_URL + "account/getorderhistory?apikey=" + this.userAPIKey + "&nonce=" + getNonce();
//            String apiSig = getHmacSHA512(url, this.userSecret);
//            return getJSONFromBittrex(url, apiSig);
//        }
//    }
//
//    public String getOrderHistory(String market) {
//        if (apiFieldsEmpty() || !checkMarket(market)) {
//            return null;
//        } else {
//            String url = BASE_URL + "account/getorderhistory?apikey=" + this.userAPIKey + "&market=" + market + "&nonce=" + getNonce();
//            String apiSig = getHmacSHA512(url, this.userSecret);
//            return getJSONFromBittrex(url, apiSig);
//        }
//    }
//
//    public String getWithdrawalHistory() {
//        if (apiFieldsEmpty()) {
//            return null;
//        } else {
//            String url = BASE_URL + "account/getwithdrawalhistory?apikey=" + this.userAPIKey + "&nonce=" + getNonce();
//            String apiSig = getHmacSHA512(url, this.userSecret);
//            return getJSONFromBittrex(url, apiSig);
//        }
//    }
//
//    public String getWithdrawalHistory(String currency) {
//        if (apiFieldsEmpty() || !checkCurrency(currency)) {
//            return null;
//        } else {
//            String url = BASE_URL + "account/getwithdrawalhistory?apikey=" + this.userAPIKey + "&currency=" + currency + "&nonce=" + getNonce();
//            String apiSig = getHmacSHA512(url, this.userSecret);
//            return getJSONFromBittrex(url, apiSig);
//        }
//    }
//
//    public String getDepositHistory() {
//        if (apiFieldsEmpty()) {
//            return null;
//        } else {
//            String url = BASE_URL + "account/getdeposithistory?apikey=" + this.userAPIKey + "&nonce=" + getNonce();
//            String apiSig = getHmacSHA512(url, this.userSecret);
//            return getJSONFromBittrex(url, apiSig);
//        }
//    }
//
//    public String getDepositHistory(String currency) {
//        if (apiFieldsEmpty() || !checkCurrency(currency)) {
//            return null;
//        } else {
//            String url = BASE_URL + "account/getdeposithistory?apikey=" + this.userAPIKey + "&currency=" + currency + "&nonce=" + getNonce();
//            String apiSig = getHmacSHA512(url, this.userSecret);
//            return getJSONFromBittrex(url, apiSig);
//        }
//    }
//
//
//    public static String getJSONFromBittrex(String requestUrl) {
//        return getJSONFromBittrex(requestUrl, "");
//    }
//
//    /** Connects to wrapper.Bittrex using an HTTP connection and url query and returns a
//     *
//     * @param requestUrl - String url request to wrapper.Bittrex without nonce and without headers
//     * @param apiSign - SHA-512 HMAC of this.usersecret and the url query
//     * @return - String of data from wrapper.Bittrex Server in json format
//     */
//    public static String getJSONFromBittrex(String requestUrl, String apiSign) {
//
//        String jsonString = "";
//        try {
//            URL bittrex = new URL(requestUrl);
//            HttpURLConnection bittrexConnection = (HttpURLConnection) bittrex.openConnection();
//            if (!apiSign.isEmpty()) {
//                bittrexConnection.setRequestProperty("apisign", apiSign);
//            }
//            BufferedReader in = new BufferedReader(new InputStreamReader(bittrexConnection.getInputStream()));
//            String inputLine;
//            while ((inputLine = in.readLine()) != null) {
//                jsonString += inputLine;
//            }
//            in.close();
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return jsonString;
//    }
//
//    /** Connect to wrapper.Bittrex and populate the initial lists of coins and transaction fees
//     *
//     */
//    private void initializeMarkets() {
//        Gson gson = new Gson();
//        List<Market> markets = gson.fromJson(this.getMarkets(), GetMarketsContainer.class).getMarkets();
//        if (markets != null) {
//            for(int i = 0; i < markets.size(); i++) {
//                Market market = markets.get(i);
//                String marketName = market.getMarketName();
//                if (marketName != null) {
//                    this.markets.put(marketName, market);
//                }
//            }
//        }
//
//        List<Currency> currencies = gson.fromJson(this.getCurrencies(), GetCurrenciesContainer.class).getCurrencies();
//        if (currencies != null) {
//            for (int i = 0; i < currencies.size(); i++) {
//                Currency currency = currencies.get(i);
//                String currencyName = currency.getCurrency();
//                Double txFee = currency.getTxFee();
//                if (!currencyName.isEmpty()) {
//                    this.txFees.put(currencyName, txFee);
//                }
//            }
//        }
//
//        this.marketStates = gson.fromJson(this.getMarketSummaries(), GetMarketSummariesContainer.class).getMarketSummaries();
//    }
//
//    /** Checks if the market is a valid market allowed to be queried
//     *
//     * @param market - Market name to be checked
//     * @return - Boolean whether market is in the wrapper.Bittrex database
//     */
//    private boolean checkMarket(String market) {
//        return this.markets.containsKey(market.toUpperCase());
//    }
//
//    /** Checks if the currency is a valid currency allowed to be queried
//     *
//     * @param currency - Currency name to be chcked
//     * @return - Boolean whether currency is in th wrapper.Bittrex database
//     */
//    private boolean checkCurrency(String currency) {
//        return this.txFees.containsKey(currency.toUpperCase());
//    }
//
//    /** Checks if the userApiKey or userSecret are empty and thus cannot possibly give a correct query to wrapper.Bittrex
//     *
//     * @return - Boolean whether apiKey or secret are invalid
//     */
//    private boolean apiFieldsEmpty() {
//        return this.userAPIKey.isEmpty() || this.userSecret.isEmpty();
//    }
//
//
//    /** Function to hash secret key to HMAC using the SHA-512 standard
//     *
//     * @param key - Key to be hashed
//     * @param message - Message to be authenticated
//     * @return - Hex string to be used in HTTP Connection Header
//     */
//public static String getHmacSHA512(String message, String key) {
//    String result = "";
//    try{
//        byte [] byteKey = key.getBytes("UTF-8");
//        final String HMAC_SHA512 = "HmacSHA512";
//        Mac sha512_HMAC = Mac.getInstance(HMAC_SHA512);
//        SecretKeySpec keySpec = new SecretKeySpec(byteKey, HMAC_SHA512);
//        sha512_HMAC.init(keySpec);
//        byte [] mac_data = sha512_HMAC.doFinal(message.getBytes("UTF-8"));
//        //result = Base64.encode(mac_data);
//        result = bytesToHex(mac_data);
//    } catch (UnsupportedEncodingException e) {
//        e.printStackTrace();
//    } catch (NoSuchAlgorithmException e) {
//        e.printStackTrace();
//    } catch (InvalidKeyException e) {
//        e.printStackTrace();
//    }
//    return result;
//}
//
//    private static String bytesToHex(byte[] bytes) {
//        final  char[] hexArray = "0123456789abcdef".toCharArray();
//        char[] hexChars = new char[bytes.length * 2];
//        for ( int j = 0; j < bytes.length; j++ ) {
//            int v = bytes[j] & 0xFF;
//            hexChars[j * 2] = hexArray[v >>> 4];
//            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
//        }
//        return new String(hexChars);
//    }
//
//    /** Make new nonce on every call
//     *
//     * @return - Nonce string to use for HTTP Connection request
//     */
//    private static String getNonce() {
//        return Integer.toString((int) (Math.floor(new Date().getTime())));
//    }
//
//    /** Takes a Json formatted string and prints it to console in a readable format.
//     *  Intended for use with JSON strings that are large and likely in a single line.
//     *
//     * @param jsonString - String of JSON intended to be printed
//     */
//    public static void printJson(String jsonString) {
//        int numTabs = 0;
//        final int indentByAmt = 4;
//        boolean inQuotes = false;
//        String line = "";
//        for (int i = 0; i < jsonString.length(); i++) {
//            char token = jsonString.charAt(i);
//            if (token == '{') {
//                System.out.println(line + token);
//                numTabs++;
//                line = String.format("%" + numTabs*indentByAmt + "s", " ");
//            } else if (token == '}') {
//                System.out.println(line);
//                numTabs--;
//                if (numTabs > 0) {
//                    line = String.format("%" + numTabs * indentByAmt + "s", " ") + token;
//                } else {
//                    line = "" + '}';
//                }
//            } else if (token == ',') {
//                System.out.println(line + token);
//                line = String.format("%" + numTabs * indentByAmt + "s", " ");
//            } else if (token == ':') {
//                if (!inQuotes) {
//                    line += " : ";
//                } else {
//                    line += token;
//                }
//            } else if (token == '"'){
//                inQuotes = !inQuotes;
//                line += token;
//            } else {
//                line += token;
//            }
//
//        }
//        System.out.println(line);
//    }
//
//    /**
//     *
//     * @return - The set of names of available markets
//     */
//    public Set<String> getMarketSet() {
//        return this.markets.keySet();
//    }
//
//    /**
//     *
//     * @param market
//     * @return
//     */
//    public Market getMarket(String market) {
//        if (checkMarket(market)) {
//            return this.markets.get(market);
//        } else {
//            return null;
//        }
//    }
//
//    public List<MarketSummary> getMarketStates() {
//        return this.marketStates;
//    }
//
//    public double getTransactionFee(String currency) {
//        if (checkCurrency(currency)) {
//            return txFees.get(currency);
//        } else {
//            return Double.NEGATIVE_INFINITY;
//        }
//    }

}
