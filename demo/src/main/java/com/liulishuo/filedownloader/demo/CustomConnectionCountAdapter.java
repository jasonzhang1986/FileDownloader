/*
 * Copyright (c) 2015 LingoChamp Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.liulishuo.filedownloader.demo;

import com.liulishuo.filedownloader.util.FileDownloadHelper;

/**
 * Created by zhangjifeng on 2017/12/20.
 */

public class CustomConnectionCountAdapter implements FileDownloadHelper.ConnectionCountAdapter {
    // 1 connection: [0, 1MB)
    private final static long ONE_CONNECTION_UPPER_LIMIT = 1024 * 1024; // 1MB
    // 3 connection: [1MB, 5MB)
    private final static long TWO_CONNECTION_UPPER_LIMIT = 5 * 1024 * 1024; // 5MB
    // 5connection: [5MB, 50MB)
    private final static long THREE_CONNECTION_UPPER_LIMIT = 50 * 1024 * 1024; // 50MB
    // 8 connection: [50MB, 100MB)
    private final static long FOUR_CONNECTION_UPPER_LIMIT = 100 * 1024 * 1024; // 100MB

    @Override
    public int determineConnectionCount(int downloadId, String url, String path, long totalLength) {
        if (totalLength < ONE_CONNECTION_UPPER_LIMIT) {
            return 1;
        }

        if (totalLength < TWO_CONNECTION_UPPER_LIMIT) {
            return 3;
        }

        if (totalLength < THREE_CONNECTION_UPPER_LIMIT) {
            return 5;
        }

        if (totalLength < FOUR_CONNECTION_UPPER_LIMIT) {
            return 8;
        }

        return 10;
    }
}
