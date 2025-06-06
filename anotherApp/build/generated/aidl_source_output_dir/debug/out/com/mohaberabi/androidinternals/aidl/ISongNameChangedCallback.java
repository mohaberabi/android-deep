/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Using: /Users/mohaberabi/Library/Android/sdk/build-tools/35.0.0/aidl -p/Users/mohaberabi/Library/Android/sdk/platforms/android-35/framework.aidl -o/Users/mohaberabi/development/projects/AndroidInternals/anotherApp/build/generated/aidl_source_output_dir/debug/out -I/Users/mohaberabi/development/projects/AndroidInternals/anotherApp/src/main/aidl -I/Users/mohaberabi/development/projects/AndroidInternals/anotherApp/src/debug/aidl -I/Users/mohaberabi/.gradle/caches/8.10.2/transforms/5691e77c1972fde26de3116018e5a7cc/transformed/core-1.16.0/aidl -I/Users/mohaberabi/.gradle/caches/8.10.2/transforms/d340a55c8dd93c92972bd0b1acc55093/transformed/versionedparcelable-1.1.1/aidl -d/var/folders/gx/qrb5f3550fd4q2ldmtvdt2tm0000gn/T/aidl970956542092055908.d /Users/mohaberabi/development/projects/AndroidInternals/anotherApp/src/main/aidl/com/mohaberabi/androidinternals/aidl/ISongNameChangedCallback.aidl
 */
package com.mohaberabi.androidinternals.aidl;
public interface ISongNameChangedCallback extends android.os.IInterface
{
  /** Default implementation for ISongNameChangedCallback. */
  public static class Default implements com.mohaberabi.androidinternals.aidl.ISongNameChangedCallback
  {
    @Override public void onSongNameChanged(java.lang.String name) throws android.os.RemoteException
    {
    }
    @Override
    public android.os.IBinder asBinder() {
      return null;
    }
  }
  /** Local-side IPC implementation stub class. */
  public static abstract class Stub extends android.os.Binder implements com.mohaberabi.androidinternals.aidl.ISongNameChangedCallback
  {
    /** Construct the stub at attach it to the interface. */
    @SuppressWarnings("this-escape")
    public Stub()
    {
      this.attachInterface(this, DESCRIPTOR);
    }
    /**
     * Cast an IBinder object into an com.mohaberabi.androidinternals.aidl.ISongNameChangedCallback interface,
     * generating a proxy if needed.
     */
    public static com.mohaberabi.androidinternals.aidl.ISongNameChangedCallback asInterface(android.os.IBinder obj)
    {
      if ((obj==null)) {
        return null;
      }
      android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
      if (((iin!=null)&&(iin instanceof com.mohaberabi.androidinternals.aidl.ISongNameChangedCallback))) {
        return ((com.mohaberabi.androidinternals.aidl.ISongNameChangedCallback)iin);
      }
      return new com.mohaberabi.androidinternals.aidl.ISongNameChangedCallback.Stub.Proxy(obj);
    }
    @Override public android.os.IBinder asBinder()
    {
      return this;
    }
    @Override public boolean onTransact(int code, android.os.Parcel data, android.os.Parcel reply, int flags) throws android.os.RemoteException
    {
      java.lang.String descriptor = DESCRIPTOR;
      if (code >= android.os.IBinder.FIRST_CALL_TRANSACTION && code <= android.os.IBinder.LAST_CALL_TRANSACTION) {
        data.enforceInterface(descriptor);
      }
      if (code == INTERFACE_TRANSACTION) {
        reply.writeString(descriptor);
        return true;
      }
      switch (code)
      {
        case TRANSACTION_onSongNameChanged:
        {
          java.lang.String _arg0;
          _arg0 = data.readString();
          this.onSongNameChanged(_arg0);
          reply.writeNoException();
          break;
        }
        default:
        {
          return super.onTransact(code, data, reply, flags);
        }
      }
      return true;
    }
    private static class Proxy implements com.mohaberabi.androidinternals.aidl.ISongNameChangedCallback
    {
      private android.os.IBinder mRemote;
      Proxy(android.os.IBinder remote)
      {
        mRemote = remote;
      }
      @Override public android.os.IBinder asBinder()
      {
        return mRemote;
      }
      public java.lang.String getInterfaceDescriptor()
      {
        return DESCRIPTOR;
      }
      @Override public void onSongNameChanged(java.lang.String name) throws android.os.RemoteException
      {
        android.os.Parcel _data = android.os.Parcel.obtain();
        android.os.Parcel _reply = android.os.Parcel.obtain();
        try {
          _data.writeInterfaceToken(DESCRIPTOR);
          _data.writeString(name);
          boolean _status = mRemote.transact(Stub.TRANSACTION_onSongNameChanged, _data, _reply, 0);
          _reply.readException();
        }
        finally {
          _reply.recycle();
          _data.recycle();
        }
      }
    }
    static final int TRANSACTION_onSongNameChanged = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
  }
  /** @hide */
  public static final java.lang.String DESCRIPTOR = "com.mohaberabi.androidinternals.aidl.ISongNameChangedCallback";
  public void onSongNameChanged(java.lang.String name) throws android.os.RemoteException;
}
