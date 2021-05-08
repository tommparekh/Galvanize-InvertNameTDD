package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class NameInverter {

    public String invertName(String name) {

        name = name.trim();

        if (name == null || name.isEmpty() || name.equals("")) {
            return "Please insert a name";
        }

        if (!name.contains(" ")) {
            return name;
        }

        String[] processedNames = cleanUpNames(name.split(" "));

        int prefixIndex = checkForPrefix(processedNames);
        int suffixIndex = checkForSuffix(processedNames);

        if (prefixIndex >= 0 && suffixIndex >= 0) {
            boolean isPrefixValid = checkIsPrefixValid(prefixIndex);
            boolean isSuffixValid = checkIsSuffixValid(processedNames, suffixIndex);
            if (!isPrefixValid || !isSuffixValid) {
                return "Invalid name";
            } else {
                switch (processedNames.length) {
                    case 4:
                        return processedNames[0] + " " + processedNames[2] + ", " + processedNames[1] + " " + processedNames[3];
                    case 3:
                        return processedNames[0] + " " + processedNames[1] + " " + processedNames[2];
                    default:
                        return "Invalid name";
                }

            }
        } else if (prefixIndex >= 0) {
            boolean isPrefixValid = checkIsPrefixValid(prefixIndex);
            if (isPrefixValid) {
                switch (processedNames.length) {
                    case 3:
                        return processedNames[0] + " " + processedNames[2] + ", " + processedNames[1];
                    case 2:
                        return processedNames[0] + " " + processedNames[1];
                    default:
                        return "Invalid name";
                }
            } else {
                return "Invalid name";
            }
        } else if (suffixIndex >= 0) {
            boolean isSuffixValid = checkIsSuffixValid(processedNames, suffixIndex);
            if (isSuffixValid) {
                switch (processedNames.length) {
                    case 3:
                        return processedNames[1] + ", " + processedNames[0] + " " + processedNames[2];
                    case 2:
                        return processedNames[0] + " " + processedNames[1];
                    default:
                        return "Invalid name";
                }
            } else {
                return "Invalid name";
            }
        }

        if (processedNames.length == 2) {
            return processedNames[1] + ", " + processedNames[0];
        }

        return "Invalid name";
    }

    private boolean checkIsSuffixValid(String[] processedNames, int suffixIndex) {
        return suffixIndex == processedNames.length - 1;
    }

    private int checkForSuffix(String[] processedNames) {
        Optional<String> index = Arrays.stream(processedNames)
                .filter((name) ->
                        name.equals(SUFFIX.Sr.notation) || name.equals(SUFFIX.Jr.notation)
                                || name.equals(SUFFIX.II.notation) || name.equals(SUFFIX.III.notation)
                ).findFirst();
        if (index.isPresent()) {
            int retIndex = Arrays.asList(processedNames).indexOf(index.get());
            return retIndex;
        }

        return -1;
    }

    private int checkForPrefix(String[] processedNames) {
        Optional<String> index = Arrays.stream(processedNames)
                .filter((name) ->
                        name.equals(PREFIX.Mr.notation) || name.equals(PREFIX.Mrs.notation)
                                || name.equals(PREFIX.Ms.notation)
                ).findFirst();
        if (index.isPresent()) {
            int retIndex = Arrays.asList(processedNames).indexOf(index.get());
            return retIndex;
        }

        return -1;
    }

    private boolean checkIsPrefixValid(int index) {
//       if (processedNames[0].equalsIgnoreCase(PREFIX.Mr.getNotation())
//               || processedNames[0].equalsIgnoreCase(PREFIX.Mrs.getNotation())
//               || processedNames[0].equalsIgnoreCase(PREFIX.Ms.getNotation())) {
//                   return true;
//        }

        return index == 0;
    }

    private String[] cleanUpNames(String[] names) {
        List<String> tNames = Arrays.stream(names).filter((str) -> !(str.equals(" ") || str.isEmpty()))
                .collect(Collectors.toCollection(ArrayList<String>::new));
        return tNames.toArray(new String[tNames.size()]);
    }

    private enum PREFIX {
        Mr("Mr."), Mrs("Mrs."), Ms("Ms.");

        String notation = null;

        PREFIX(String notation) {
            this.notation = notation;
        }

        public String getNotation() {
            return this.notation;
        }
    }

    private enum SUFFIX {
        Sr("Sr."), Jr("Jr."), II("II"), III("III");

        String notation = null;

        SUFFIX(String notation) {
            this.notation = notation;
        }

        public String getNotation() {
            return this.notation;
        }
    }

}
