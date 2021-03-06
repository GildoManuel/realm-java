/*
 * Copyright 2020 Realm Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.realm.internal.log.obfuscator;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Obfuscator for API key-related login requests. It will replace the {@code "key":"<KEY>"} pattern
 * with {@code "key":"***"}.
 */

public class ApiKeyObfuscator extends RegexPatternObfuscator {

    public static final String API_KEY_KEY = "key";

    private ApiKeyObfuscator(Map<Pattern, String> patternReplacementMap) {
        super(patternReplacementMap);
    }

    /**
     * Creates a {@link RegexPatternObfuscator} for API keys.
     *
     * @return an obfuscator that keeps API key information from being displayed in the logcat.
     */
    public static ApiKeyObfuscator obfuscator() {
        return new ApiKeyObfuscator(getPatterns());
    }

    private static Map<Pattern, String> getPatterns() {
        Map<Pattern, String> map = new HashMap<>();
        map.put(Pattern.compile("((\"" + API_KEY_KEY + "\"):(\\s?\".+?\"))"), "\"" + API_KEY_KEY + "\":\"***\"");
        return map;
    }
}
