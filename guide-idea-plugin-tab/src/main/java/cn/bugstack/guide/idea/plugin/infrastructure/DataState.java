package cn.bugstack.guide.idea.plugin.infrastructure;

import java.util.ArrayList;
import java.util.List;

public class DataState {

    private List<String> gids = new ArrayList<>();

    public List<String> getGids() {
        return gids;
    }

    public void setGids(List<String> gids) {
        this.gids = gids;
    }

}
