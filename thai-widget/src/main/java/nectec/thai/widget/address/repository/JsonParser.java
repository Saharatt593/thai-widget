/*
 * Copyright (c) 2016 NECTEC
 *   National Electronics and Computer Technology Center, Thailand
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package nectec.thai.widget.address.repository;

import android.content.Context;
import android.util.Log;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import th.or.nectec.thai.widget.BuildConfig;

final class JsonParser {

    private static final String TAG = "JsonParser";

    private JsonParser() {
    }

    public static <T> List<T> parse(Context context, String jsonFileName, Class<T> tClass) {
        List<T> allProvince = new ArrayList<>();
        Gson gson = new Gson();
        try {
            InputStream inputStream = context.getAssets().open(jsonFileName);
            JsonReader reader = new JsonReader(new InputStreamReader(inputStream, "UTF-8"));
            reader.beginArray();
            while (reader.hasNext()) {
                T message = gson.fromJson(reader, tClass);
                allProvince.add(message);
            }
            reader.endArray();
            reader.close();
        } catch (IOException e) {
            if (BuildConfig.DEBUG) Log.e(TAG, "parse() error.", e);
        }
        return allProvince;
    }
}
