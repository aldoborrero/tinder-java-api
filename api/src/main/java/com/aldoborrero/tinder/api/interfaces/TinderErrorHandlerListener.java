package com.aldoborrero.tinder.api.interfaces;

import com.aldoborrero.tinder.api.retrofit.TinderErrorHandler;

/**
 * Created by terro on 26/01/15.
 */
public interface TinderErrorHandlerListener {

    public void onError (TinderErrorHandler.ERROR typeError);

}
