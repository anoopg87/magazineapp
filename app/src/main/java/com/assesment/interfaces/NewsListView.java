package com.assesment.interfaces;
import com.assesment.model.Facts;



/* View for the NewsListFragment
onSuccess method send the response to the presenter to show items in RecyclerView
onError send error indication from the server to the presenter
sendConnectionError send network error indications to the presenter
 */
public interface NewsListView {

     void onSuccess(Facts response);
     void onError();
    void sendConnectionError();

}
