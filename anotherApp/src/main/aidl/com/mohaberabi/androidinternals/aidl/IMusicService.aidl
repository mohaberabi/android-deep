package com.mohaberabi.androidinternals.aidl;
import com.mohaberabi.androidinternals.aidl.ISongNameChangedCallback;
 interface IMusicService {
    void next();

    void previous();

    String getCurrentSongName();
    void registerCallBack(ISongNameChangedCallback callback );
        void unregisterCallBack(ISongNameChangedCallback callback );

}
