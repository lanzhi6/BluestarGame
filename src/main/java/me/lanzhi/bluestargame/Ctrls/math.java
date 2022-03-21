package me.lanzhi.bluestargame.Ctrls;

public class math {
    public static String[] yunsuan(String str) {
        int cntnum = 0;
        String[] ans = new String[5];
        String s = "";
        char[] a = new char[100];
        String[] jieguo = new String[100];
        int top = -1;
        int j = 0;
        for (int i = 0; i < str.length(); i++) {
            if ("0123456789.".indexOf(str.charAt(i)) >= 0) {
                s = "";
                for (; (i < str.length()) && ("0123456789.".indexOf(str.charAt(i)) >= 0); i++) {
                    s = s + str.charAt(i);
                }
                i--;
                jieguo[j] = s;
                j++;
                cntnum++;
                if (cntnum > 4) {
                    return null;
                }
                ans[cntnum] = s;
            } else if ("(".indexOf(str.charAt(i)) >= 0) {
                top++;
                a[top] = str.charAt(i);
            } else if (")".indexOf(str.charAt(i)) >= 0) {


                while (a[top] != '(') {
                    jieguo[j] = (a[top] + "");
                    j++;
                    top--;
                }


                top--;


            } else if ("*/×÷".indexOf(str.charAt(i)) >= 0) {
                if (top == -1) {
                    top++;
                    a[top] = str.charAt(i);


                } else if ("*/×÷".indexOf(a[top]) >= 0) {
                    jieguo[j] = (a[top] + "");
                    j++;
                    a[top] = str.charAt(i);
                } else if ("(".indexOf(a[top]) >= 0) {
                    top++;
                    a[top] = str.charAt(i);
                } else if ("+-".indexOf(a[top]) >= 0) {
                    top++;
                    a[top] = str.charAt(i);
                }

            } else if ("+-".indexOf(str.charAt(i)) >= 0) {
                if (top == -1) {
                    top++;
                    a[top] = str.charAt(i);


                } else if ("*/×÷".indexOf(a[top]) >= 0) {
                    jieguo[j] = (a[top] + "");
                    j++;
                    a[top] = str.charAt(i);
                } else if ("(".indexOf(a[top]) >= 0) {
                    top++;
                    a[top] = str.charAt(i);
                } else if ("+-".indexOf(a[top]) >= 0) {
                    jieguo[j] = (a[top] + "");
                    j++;
                    a[top] = str.charAt(i);
                }

            } else {
                return null;
            }
        }
        while (top != -1) {
            jieguo[j] = (a[top] + "");
            j++;
            top--;
        }
        ans[0] = Result(jieguo);
        return ans;
    }

    public static String Result(String[] str) {
        if (str == null) {
            return "-1";
        }
        String[] Result = new String[100];
        int Top = -1;
        for (int i = 0; str[i] != null; i++) {
            if ("+-*/×÷".indexOf(str[i]) < 0) {
                Top++;
                Result[Top] = str[i];
            }
            if ("+-*/×÷".indexOf(str[i]) >= 0) {

                double x = Double.parseDouble(Result[Top]);
                Top--;
                double y = Double.parseDouble(Result[Top]);
                Top--;
                if ("-".indexOf(str[i]) >= 0) {
                    double n = y - x;
                    Top++;
                    Result[Top] = String.valueOf(n);
                }
                if ("+".indexOf(str[i]) >= 0) {
                    double n = y + x;
                    Top++;
                    Result[Top] = String.valueOf(n);
                }
                if ("*×".indexOf(str[i]) >= 0) {
                    double n = y * x;
                    Top++;
                    Result[Top] = String.valueOf(n);
                }
                if ("/÷".indexOf(str[i]) >= 0) {
                    if (x == 0.0D) {
                        String s = "ERROR";
                        return s;
                    }


                    double n = y / x;
                    Top++;
                    Result[Top] = String.valueOf(n);
                }
            }
        }
        return Result[Top];
    }
}