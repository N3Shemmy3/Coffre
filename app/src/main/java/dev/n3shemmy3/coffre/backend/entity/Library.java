/*
 *
 *  * Copyright (C) 2025 Shemmy
 *  *
 *  * This program is free software: you can redistribute it and/or modify
 *  * it under the terms of the GNU General Public License as published by
 *  * the Free Software Foundation, version 3 of the License.
 *  *
 *  * This program is distributed in the hope that it will be useful,
 *  * but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  * GNU General Public License for more details.
 *  *
 *  * You should have received a copy of the GNU General Public License
 *  * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 *
 */

package dev.n3shemmy3.coffre.backend.entity;

import androidx.annotation.NonNull;

import java.util.Objects;

public class Library {
    private String author;
    private String authorWebsite;
    private String name;
    private String packageName;
    private String description;
    private String version;
    private String website;
    private String license;

    public Library() {
    }

    public Library(String author, String authorWebsite, String name, String packageName, String description, String version, String website, String license) {
        this.author = author;
        this.authorWebsite = authorWebsite;
        this.name = name;
        this.packageName = packageName;
        this.description = description;
        this.version = version;
        this.website = website;
        this.license = license;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAuthorWebsite() {
        return authorWebsite;
    }

    public void setAuthorWebsite(String authorWebsite) {
        this.authorWebsite = authorWebsite;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof Library)) return false;

        Library library = (Library) o;
        return Objects.equals(getAuthor(), library.getAuthor()) && Objects.equals(getAuthorWebsite(), library.getAuthorWebsite()) && Objects.equals(getName(), library.getName()) && Objects.equals(getPackageName(), library.getPackageName()) && Objects.equals(getDescription(), library.getDescription()) && Objects.equals(getVersion(), library.getVersion()) && Objects.equals(getWebsite(), library.getWebsite()) && Objects.equals(getLicense(), library.getLicense());
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(getAuthor());
        result = 31 * result + Objects.hashCode(getAuthorWebsite());
        result = 31 * result + Objects.hashCode(getName());
        result = 31 * result + Objects.hashCode(getPackageName());
        result = 31 * result + Objects.hashCode(getDescription());
        result = 31 * result + Objects.hashCode(getVersion());
        result = 31 * result + Objects.hashCode(getWebsite());
        result = 31 * result + Objects.hashCode(getLicense());
        return result;
    }

    @NonNull
    @Override
    public String toString() {
        return "Library{" +
                "author='" + author + '\'' +
                ", authorWebsite='" + authorWebsite + '\'' +
                ", name='" + name + '\'' +
                ", packageName='" + packageName + '\'' +
                ", description='" + description + '\'' +
                ", version='" + version + '\'' +
                ", website='" + website + '\'' +
                ", license='" + license + '\'' +
                '}';
    }
}
