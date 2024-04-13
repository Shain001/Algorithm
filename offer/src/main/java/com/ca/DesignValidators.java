package com.ca;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Design
 *
 *
 * Page
 *  Update an element -> maintain a field/flag -> true/ false
 *
 *  Hash -> 2 different hasme
 *  conflict HashMap<>9 equals</>
 *
 *
 *
 *
 *  checkFront()  -> 1. maintain the fonts used by pages in desgin, so that we don't have to itereate all every time
 *
 *  PageValidator -< class()
 *
 *  1. overload (list<> -> rules I want )
 *
 * Element
 *  checkWidth()
 */
public class DesignValidators {
    record Design(int width, int height, List<Page> pages) {
    }

    record Page(String id, List<Element> elements) {
    }

    record Element(String id, int width, int height, String font) {
    }

    interface DesignUpdater {
        /**
         * Replaces the entire design with the provided design.
         *
         * @return true if successful, false if the design was not replaced.
         */
        boolean replaceWith(Design newDesign);

        /**
         * Adds the provided page to the existing design
         * @return true if successful, false if the design was not updated.
         */
        boolean addPage(Page newPage);
    }

    static class DesignUpdaterImpl implements DesignUpdater {
        public Design currentDesign = new Design(1000, 1000, List.of());

        @Override
        public boolean replaceWith(Design newDesign) {
            if (!checkWidthOfElemtn(newDesign)) {
                return false;
            }
            if (!checkFont(newDesign)) {
                return false;
            }
            currentDesign = newDesign;
            return true;
        }

        @Override
        public boolean addPage(Page newPage) {
            if (!validatePageWidth(currentDesign.width, newPage)) {
                return false;
            }
            if (!checkFontForNewPage(currentDesign, newPage)) {
                return false;
            }
            var currentPages = new ArrayList<>(currentDesign.pages());
            currentPages.add(newPage);
            currentDesign = new Design(currentDesign.width(), currentDesign.height(), currentPages);
            return true;
        }


        /**
         * valide if the new design is leagal.
         *
         *
         */
        static boolean checkWidthOfElemtn(Design newDesign) {
            var maxWidth = newDesign.width;

            for (Page p : newDesign.pages) {
                if (validatePageWidth(maxWidth, p)) return false;
            }

            return true;
        }

        private static boolean validatePageWidth(int maxWidth, Page p) {
            for (Element e : p.elements) {
                if (e.width > maxWidth) {
                    return true;
                }
            }
            return false;
        }

        /**
         * For every page in one design, none of them should share a same font.
         *
         * for the space comp -> O(n)
         * for the time comp -> O(P * N) -> O P*N
         *
         * Stream
         */
        static boolean checkFont(Design design) {
            var set = new HashSet<String>();

            for (Page p : design.pages) {
                var singlePageSet = new HashSet<String>();

                for (Element e : p.elements) {
                        singlePageSet.add(e.font);
                    }

                for (String front : singlePageSet) {
                    if (singlePageSet.contains(front)) {
                        return false;
                    } else {
                        singlePageSet.add(front);
                    }
                }
            }
            return true;
        }

        static boolean checkFontForNewPage(Design design, Page p) {
            var set = new HashSet<String>();

            for (Page pa : design.pages) {
                for (Element e : pa.elements) {
                    set.add(e.font);
                }
            }

            // check the new page
            for (Element e : p.elements) {
                if (set.contains(e.font)) {
                    return false;
                }
            }

            return true;
        }
    }
}
