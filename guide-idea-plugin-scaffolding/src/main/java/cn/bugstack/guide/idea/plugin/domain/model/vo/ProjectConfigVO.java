package cn.bugstack.guide.idea.plugin.domain.model.vo;

public class ProjectConfigVO {

    private String _groupId;
    private String _artifactId;
    private String _version;
    private String _package;

    public String get_groupId() {
        return _groupId;
    }

    public void set_groupId(String _groupId) {
        this._groupId = _groupId;
    }

    public String get_artifactId() {
        return _artifactId;
    }

    public void set_artifactId(String _artifactId) {
        this._artifactId = _artifactId;
    }

    public String get_version() {
        return _version;
    }

    public void set_version(String _version) {
        this._version = _version;
    }

    public String get_package() {
        return _package;
    }

    public void set_package(String _package) {
        this._package = _package;
    }

}
