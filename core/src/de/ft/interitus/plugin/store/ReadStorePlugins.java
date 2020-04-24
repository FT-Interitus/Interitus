package de.ft.interitus.plugin.store;

import de.ft.interitus.displayErrors;
import de.ft.interitus.loading.AssetLoader;
import de.ft.interitus.utils.DownloadFile;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class ReadStorePlugins {
   static String jsonfile =null;
   static int plugincounter =0;
    public static void read() {


        try {
           jsonfile = DownloadFile.downloadFile("https://raw.githubusercontent.com/FT-Interitus/Interitus-Plugins/master/plugins.json");
        } catch (IOException e) {

        }
        //System.out.println(jsonfile);
        JSONObject jsonObject =null;
        try {
            jsonObject = new JSONObject(jsonfile);
        }catch (JSONException e) {
            displayErrors.error =e;
            e.printStackTrace();
        }


try {
    for (plugincounter = plugincounter; plugincounter < 999999999; plugincounter++) {
        if (!jsonObject.has("plugin" + plugincounter)) {
            break;
        }
    }

    for(int i=0;i<plugincounter;i++) {


        int id =jsonObject.getJSONObject("plugin"+i).getInt("id");
        String name = jsonObject.getJSONObject("plugin"+i).getString("name");
        double version = jsonObject.getJSONObject("plugin"+i).getDouble("version");
        String path = jsonObject.getJSONObject("plugin"+i).getString("path");
        String description = jsonObject.getJSONObject("plugin"+i).getString("description");
        String image =jsonObject.getJSONObject("plugin"+i).getString("image");
        String detailed_description =jsonObject.getJSONObject("plugin"+i).getString("detailed_description");
        StorePluginsVar.pluginEntries.add(new StorePluginEntry(id,name,version,path,description,image,detailed_description));



    }


}catch (Exception e) {
e.printStackTrace();
}






    }

    public static int loadmore() {
        JSONObject jsonObject = new JSONObject(jsonfile);
        int oldplugin = AssetLoader.storeimages.size();
        int limit =  AssetLoader.storeimages.size()+StorePluginsVar.loadinglimit;
        for(int i= AssetLoader.storeimages.size(); i<limit;i++) {
            if(!jsonObject.has("plugin"+i)) {

                break;
            }
        }

        for(int i = oldplugin;i< limit;i++) {

            int id =jsonObject.getJSONObject("plugin"+i).getInt("id");
            String name = jsonObject.getJSONObject("plugin"+i).getString("name");
            double version = jsonObject.getJSONObject("plugin"+i).getDouble("version");
            String path = jsonObject.getJSONObject("plugin"+i).getString("path");
            String description = jsonObject.getJSONObject("plugin"+i).getString("description");
            String image =jsonObject.getJSONObject("plugin"+i).getString("image");
            String detailed_description =jsonObject.getJSONObject("plugin"+i).getString("detailed_description");
            StorePluginsVar.pluginEntries.add(new StorePluginEntry(id,name,version,path,description,image,detailed_description));
        }


        System.out.println("Plugin "+( AssetLoader.storeimages.size()-oldplugin));
        return  AssetLoader.storeimages.size()-oldplugin;

    }



}
