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

package nectec.util;

public final class TextUtils {

    private TextUtils() {
    }

    public static boolean isDigitOnly(String text) {
        return text.matches("\\d+");
    }

    public static boolean isRepeatingNumber(String text) {
        return text.matches("\\b(\\d)\\1+\\b");
    }

    public static boolean isRepeatPatternNumber(String text) {
        return text.matches("\\b(\\d+)\\1+\\b");
    }

    public static boolean isEmpty(String text) {
        return text == null || text.isEmpty();
    }

}
