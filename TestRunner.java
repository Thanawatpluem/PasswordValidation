// ดูโจทย์ วิธีทำใน README.md
// หน้าที่ของคุณ: ออกแบบ test เอง แล้วเติม check(...) ให้ครบทุก branch
public class TestRunner {

    static int pass = 0, fail = 0;

    static void check(String name, boolean ok) {
        if (ok) { pass++; System.out.println("  [PASS] " + name); }
        else    { fail++; System.out.println("  [FAIL] " + name); }
    }

    public static void main(String[] a) {
        boolean ea = false;
        assert ea = true;
        if (!ea) System.out.println("** คำเตือน: assertion ปิดอยู่ รันด้วย  java -ea TestRunner **");

        System.out.println("== Password Validation ==");

        // ตัวอย่าง assertion ปกติ (ตัวแทนกลุ่ม valid)
        check("'Abcdef12@!' valid", PasswordValidator.validate("Abcdef12@!"));

        // ตัวอย่างแพตเทิร์นทดสอบ "ต้อง throw" ด้วย try/catch
        boolean threw = false;
        try { PasswordValidator.validate(null); }
        catch (IllegalArgumentException e) { threw = true; }
        check("null -> throws IllegalArgumentException", threw == true);

        // TODO: R2 - boundary ความยาว (เช่น 7, 8, 20, 21)
        check ("pw length < 8  ->false",PasswordValidator.validate("Abcdf1@")==false);
        check ("pw length >= 8 ->true",PasswordValidator.validate("Abcdesf2g@")==true);
        check ("pw length <= 20 ->true",PasswordValidator.validate("Abcdefghijklmopq12@")==true);
        check ("pw length > 20 ->false",PasswordValidator.validate("Abcdefghijklmnopqw123")==false);
        // TODO: R3 - ไม่มีตัวพิมพ์ใหญ่ -> false
        check ("pw No uppercase -> false",PasswordValidator.validate("abcdef12@")==false);
        // TODO: R4 - ไม่มีตัวพิมพ์เล็ก -> false
        check ("pw No lowercase -> false",PasswordValidator.validate("ABCDEF12@")==false);
        // TODO: R5 - ไม่มีตัวเลข  -> false
        check ("pw No digits -> false",PasswordValidator.validate("AbcdefGH@")==false);
        // TODO: R6 - มีช่องว่าง -> false
        check ("pw Has whitespace -> false",PasswordValidator.validate("Abcdef12 @")==false);
        // TODO: boundary อื่นๆ ที่คุณคิดว่าจำเป็น
        check ("pw special char -> true",PasswordValidator.validate("Abcdef123@!")==true);
        check ("pw is empty -> false",PasswordValidator.validate("")==false);
        System.out.println("==================================");
        System.out.printf("PASS %d / FAIL %d%n", pass, fail);
        System.out.println("==================================");
        System.exit(fail == 0 ? 0 : 1);
    }
}
