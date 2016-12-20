# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in E:\Android\SDKAPI24\projectSDK/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# butterknife混淆规则
-dontwarn butterknife.internal.**
-keep class **$$ViewInjector { *; }
-keepnames class * { @butterknife.InjectView *;}


#Gson混淆规则
-keep class com.google.**{*;}
-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}
-keepattributes Signature
-keep class sun.misc.Unsafe { *; }
-keep class com.zjf.weike.bean.** { *; } #！！！gson解析的bean类包

#自定义控件其他
-keep class com.zjf.weike.widget.** { *; }

#retrofit2混淆规则
-dontwarn retrofit2.**
-keep class retrofit2.** { *; }
-keepattributes Signature
-keepattributes Exceptions

#okhttp
-dontwarn okhttp3.**
-keep class okhttp3.**{*;}
-keep interface okhttp3.**{*;}


#rx2
-dontwarn io.reactivex.**
-keep class io.reactivex.**{*;}
-keep interface io.reactivex.**{*;}

#com.jakewharton
-dontwarn com.jakewharton.**
-keep class com.jakewharton.**{*;}

#native方法
-keepclasseswithmembernames class * {
    native <methods>;
}
#glide
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
    **[] $VALUES;
      public *;
}

#友盟
-keepclassmembers class * {
   public <init> (org.json.JSONObject);
}
-keep public class com.zjf.weike.R$*{
public static final int *;
}
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}