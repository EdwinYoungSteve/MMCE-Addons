package github.alecsio.mmceaddons.common.integration.jei.wrapper;

import thaumcraft.api.aspects.AspectList;

public class AspectListWrapper extends AspectList {

    public AspectListWrapper() {
    }

    public AspectListWrapper(AspectList aspectList) {
        super();
        super.add(aspectList);
    }
}
