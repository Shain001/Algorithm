package com.interview;

import java.util.List;
public class DesignUpdaterImplTest {
    public static void main(String[] args) {
//        designUpdateShouldReplaceDesignIfSuccessful();
        designUpdateShouldNotReplaceWhenPagesUseSameFont();
    }


    static void designUpdateShouldReplaceDesignIfSuccessful() {
        var updater = new DesignValidators.DesignUpdaterImpl();
        var newDesign = new DesignValidators.Design(50, 50, List.of(new DesignValidators.Page("page-id", List.of())));
        var updated = updater.replaceWith(newDesign);
        // did replace design
        System.out.println(updated);
        System.out.println(newDesign.equals(updater.currentDesign));
    }


    static void designUpdateShouldNotReplaceWhenNewDesignHasElementsWiderThanDesign() {
        var updater = new DesignValidators.DesignUpdaterImpl();
        var element = new DesignValidators.Element("element", 50, 50, "font");
        var elementWiderThanDesign = new DesignValidators.Element("element-wider-than-design", 200, 100, "font");
        var newDesign = new DesignValidators.Design(100, 100,
                List.of(new DesignValidators.Page("page-id", List.of(element, elementWiderThanDesign))));
        var updated = updater.replaceWith(newDesign);
        // did not replace design
        System.out.println(!updated);
        System.out.println(!newDesign.equals(updater.currentDesign));
    }

    static void designUpdateShouldNotReplaceWhenPagesUseSameFont() {
        var updater = new DesignValidators.DesignUpdaterImpl();
        var element = new DesignValidators.Element("element", 50, 50, "font");
        var element2 = new DesignValidators.Element("element-wider-than-design", 50, 50, "font");
        var newDesign = new DesignValidators.Design(100, 100,
                List.of(new DesignValidators.Page("page-1", List.of(element)), new DesignValidators.Page("page-2", List.of(element2))));
        var updated = updater.replaceWith(newDesign);
        // did not replace design
        System.out.println(!updated);
        System.out.println(!newDesign.equals(updater.currentDesign));
    }


}
