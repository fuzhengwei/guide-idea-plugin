package cn.bugstack.guide.idea.plugin.infrastructure;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;

public class Utils {

    /**
     * 获取剪切板数据，也就是 Ctrl + C
     */
    public static String getSystemClipboardText() {
        String ret = "";
        Clipboard sysClip = Toolkit.getDefaultToolkit().getSystemClipboard();

        // 获取剪切板中的内容
        Transferable clipTf = sysClip.getContents(null);

        if (null == clipTf) {
            return ret;
        }

        // 检查内容是否是文本类型
        if (clipTf.isDataFlavorSupported(DataFlavor.stringFlavor)) {
            try {
                ret = (String) clipTf.getTransferData(DataFlavor.stringFlavor);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return ret;
    }

    public static int getWordStartOffset(CharSequence editorText, int cursorOffset) {
        if (editorText.length() == 0) {
            return 0;
        }

        if (cursorOffset > 0 && !Character.isJavaIdentifierPart(editorText.charAt(cursorOffset))
                && Character.isJavaIdentifierPart(editorText.charAt(cursorOffset - 1))) {
            cursorOffset--;
        }

        if (Character.isJavaIdentifierPart(editorText.charAt(cursorOffset))) {
            int start = cursorOffset;
            int end = cursorOffset;

            // 定位开始位置
            while (start > 0 && Character.isJavaIdentifierPart(editorText.charAt(start - 1))) {
                start--;
            }
            return start;

        }

        return 0;

    }


}
