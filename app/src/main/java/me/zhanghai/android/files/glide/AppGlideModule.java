/*
 * Copyright (c) 2018 Hai Zhang <dreaming.in.code.zh@gmail.com>
 * All Rights Reserved.
 */

package me.zhanghai.android.files.glide;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.ParcelFileDescriptor;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Registry;
import com.bumptech.glide.annotation.GlideModule;

import java.io.File;
import java.io.InputStream;
import java.nio.ByteBuffer;

import androidx.annotation.NonNull;
import java8.nio.file.Path;

@GlideModule
public class AppGlideModule extends com.bumptech.glide.module.AppGlideModule {

    @Override
    public boolean isManifestParsingEnabled() {
        return false;
    }

    @Override
    public void registerComponents(@NonNull Context context, @NonNull Glide glide,
                                   @NonNull Registry registry) {
        registry.prepend(String.class, ByteBuffer.class,
                new MediaEmbeddedPictureModelLoader.Factory<>());
        registry.prepend(File.class, ByteBuffer.class,
                new MediaEmbeddedPictureModelLoader.Factory<>());
        registry.prepend(Path.class, ByteBuffer.class,
                new MediaEmbeddedPictureModelLoader.Factory<>());
        registry.prepend(String.class, Drawable.class, new ApkIconModelLoader.Factory<>(context));
        registry.prepend(File.class, Drawable.class, new ApkIconModelLoader.Factory<>(context));
        registry.prepend(Path.class, Drawable.class, new ApkIconModelLoader.Factory<>(context));
        registry.append(Path.class, InputStream.class, new PathInputStreamModelLoader.Factory());
        registry.append(Path.class, ParcelFileDescriptor.class,
                new PathParcelFileDescriptorModelLoader.Factory());
    }
}
