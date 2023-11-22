package algonquin.cst2355.androiddictionary;

import algonquin.cst2355.androiddictionary.Models.APIResponse;

public interface OnFetchDataListener {
    void onFetchData(APIResponse apiResponse, String message);
    void onError(String message);
}