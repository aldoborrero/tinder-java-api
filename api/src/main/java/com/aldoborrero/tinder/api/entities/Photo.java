/*
 * Copyright 2015 Aldo Borrero <aldo@aldoborrero.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.aldoborrero.tinder.api.entities;

import java.util.List;

public class Photo {

    private String id;
    private String url;
    private List<ProcessedFile> processedFiles;
    private Extension extension;
    private String filename;
    private boolean main;

    public Photo() {
    }

    public Photo(String url, List<ProcessedFile> processedFiles, Extension extension) {
        this.url = url;
        this.processedFiles = processedFiles;
        this.extension = extension;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<ProcessedFile> getProcessedFiles() {
        return processedFiles;
    }

    public void setProcessedFiles(List<ProcessedFile> processedFiles) {
        this.processedFiles = processedFiles;
    }

    public Extension getExtension() {
        return extension;
    }

    public void setExtension(Extension extension) {
        this.extension = extension;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
    
    public boolean isMain() {
        return main;
    }

    public void setMain(boolean main) {
        this.main = main;
    }
    
    @Override
    public String toString() {
        return "Photo{" +
                "url='" + url + '\'' +
                ", processedFiles=" + processedFiles +
                '}';
    }

    public enum Extension {
        JPG,
        PNG,
        WEBP
    }
    
}
