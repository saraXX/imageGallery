package android.guide.imagegallery;

import android.net.Uri;

public class ImagesGallery {
    private final Uri uri;
    private final String name;
    private final int width;
    private final int high;
    private final String date;
    private final String type;
    private final int size;

    public ImagesGallery(Uri uri, String name, int width, int high, String date, String type, int size) {
        this.uri = uri;
        this.name = name;
        this.width = width;
        this.high = high;
        this.date = date;
        this.type = type;
        this.size = size;
    }

    public String getType() {
        return type;
    }

    public Uri getUri() {
        return uri;
    }

    public String getName() {
        return name;
    }

    public int getWidth() {
        return width;
    }

    public int getHigh() {
        return high;
    }


    public String getDate() {
        return date;
    }

    public int getSize() {
        return size;
    }



}
