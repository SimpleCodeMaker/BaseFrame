package com.hyphenate.chatview.chatuidemo.widget;

import android.content.Context;
import android.widget.BaseAdapter;

import com.hyphenate.chat.EMMessage;
import com.hyphenate.chatview.Constant;
import com.hyphenate.chatview.DemoHelper;
import com.hyphenate.easeui.widget.chatrow.EaseChatRow;
import com.hyphenate.easeui.widget.presenter.EaseChatRowPresenter;

/**
 * Created by zhangsong on 17-10-12.
 */

public class ChatRowConferenceInvitePresenter extends EaseChatRowPresenter {
    @Override
    protected EaseChatRow onCreateChatRow(Context cxt, EMMessage message, int position, BaseAdapter adapter) {
        return new ChatRowConferenceInvite(cxt, message, position, adapter);
    }

    @Override
    public void onBubbleClick(EMMessage message) {
        super.onBubbleClick(message);

        String confId = message.getStringAttribute(Constant.MSG_ATTR_CONF_ID, "");
        String confPassword = message.getStringAttribute(Constant.MSG_ATTR_CONF_PASS,"");
        String extension = message.getStringAttribute(Constant.MSG_ATTR_EXTENSION, "");
        DemoHelper.getInstance().goConference(confId, confPassword, extension);
    }

}
