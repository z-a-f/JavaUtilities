package cc.zafar.parseutilities;

import android.widget.EditText;

/**
 * Created by ZafarTakhirov on 11/9/14.
 */
public final class WidgetUtil {
	private WidgetUtil(){}

	public static boolean isEmpty(EditText etText) {
		return !(etText.getText().toString().trim().length() > 0);
	}
}
