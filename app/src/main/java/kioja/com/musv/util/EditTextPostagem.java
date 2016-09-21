package kioja.com.musv.util;

import android.content.Context;
import android.view.KeyEvent;
import android.widget.EditText;

/**
 * Created by Henrique on 05/08/2015.
 */
public class EditTextPostagem extends EditText
{
    public EditTextPostagem(Context context) {
        super(context);
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if (keyCode==KeyEvent.KEYCODE_ENTER)
        {
            // Just ignore the [Enter] key
            return true;
        }
        // Handle all other keys in the default way
        return super.onKeyDown(keyCode, event);
    }
}