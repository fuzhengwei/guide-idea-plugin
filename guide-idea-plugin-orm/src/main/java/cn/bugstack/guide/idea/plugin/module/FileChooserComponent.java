package cn.bugstack.guide.idea.plugin.module;

import com.intellij.openapi.fileChooser.FileChooser;
import com.intellij.openapi.fileChooser.FileChooserDescriptor;
import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author: 小傅哥，微信：fustack
 * @github: https://github.com/fuzhengwei
 * @Copyright: 公众号：bugstack虫洞栈 | 博客：https://bugstack.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public class FileChooserComponent {

    private final Project project;

    private final FileEditorManager fileEditorManager;

    public FileChooserComponent(Project project) {
        this.project = project;
        this.fileEditorManager = FileEditorManager.getInstance(project);
    }

    public static FileChooserComponent getInstance(@NotNull Project project) {
        return new FileChooserComponent(project);
    }

    public VirtualFile showFolderSelectionDialog(@NotNull String title, @Nullable VirtualFile toSelect, @Nullable VirtualFile... roots) {
        final FileChooserDescriptor descriptor = FileChooserDescriptorFactory.createSingleFolderDescriptor();
        descriptor.setTitle(title);
        if (null != roots) {
            descriptor.setRoots(roots);
        }
        return FileChooser.chooseFile(descriptor, project, toSelect);
    }

}
