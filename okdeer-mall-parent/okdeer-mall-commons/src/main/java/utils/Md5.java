package utils;

public class Md5 {

    public static void main(String[] arg) {
        Md5 md5 = new Md5();
        System.out.println(md5.getMD5ofStr("00192535"));
        // 2B9C4285C43F044D1A99801F284CE31B  31B5FBF510C2C53A8C807E7C7225F522
        System.out.println(md5.getMD5ofStr("123456"));
    }

    public String getMD5ofStr(String s) {
        _$2();
        _$1(s.getBytes(), s.length());
        _$1();
        digestHexStr = "";
        for (int i = 0; i < 16; i++) {
            digestHexStr = new StringBuffer().append(this.digestHexStr).append(byteHEX(_$1[i])).toString();
        }
        return digestHexStr;
    }

    public Md5() {
        _$4 = new long[4];
        _$3 = new long[2];
        _$2 = new byte[64];
        _$1 = new byte[16];
        _$2();
    }

    private void _$2() {
        _$3[0] = 0L;
        _$3[1] = 0L;
        _$4[0] = 0x67452301L;
        _$4[1] = 0xefcdab89L;
        _$4[2] = 0x98badcfeL;
        _$4[3] = 0x10325476L;
    }

    private long _$4(long l, long l1, long l2) {
        return l & l1 | ~l & l2;
    }

    private long _$3(long l, long l1, long l2) {
        return l & l2 | l1 & ~l2;
    }

    private long _$2(long l, long l1, long l2) {
        return l ^ l1 ^ l2;
    }

    private long _$1(long l, long l1, long l2) {
        return l1 ^ (l | ~l2);
    }

    private long _$4(long l, long l1, long l2, long l3, long l4, long l5, long l6) {
        l += _$4(l1, l2, l3) + l4 + l6;
        l = (int) l << (int) l5 | (int) l >>> (int) (32L - l5);
        l += l1;
        return l;
    }

    private long _$3(long l, long l1, long l2, long l3, long l4, long l5, long l6) {
        l += _$3(l1, l2, l3) + l4 + l6;
        l = (int) l << (int) l5 | (int) l >>> (int) (32L - l5);
        l += l1;
        return l;
    }

    private long _$2(long l, long l1, long l2, long l3, long l4, long l5, long l6) {
        l += _$2(l1, l2, l3) + l4 + l6;
        l = (int) l << (int) l5 | (int) l >>> (int) (32L - l5);
        l += l1;
        return l;
    }

    private long _$1(long l, long l1, long l2, long l3, long l4, long l5, long l6) {
        l += _$1(l1, l2, l3) + l4 + l6;
        l = (int) l << (int) l5 | (int) l >>> (int) (32L - l5);
        l += l1;
        return l;
    }

    private void _$1(byte abyte0[], int i) {
        byte abyte1[] = new byte[64];
        int k = (int) (_$3[0] >>> 3) & 0x3f;
        if ((_$3[0] += i << 3) < (i << 3))
            _$3[1]++;
        _$3[1] += i >>> 29;
        int l = 64 - k;
        int j;
        if (i >= l) {
            _$1(_$2, abyte0, k, 0, l);
            _$1(_$2);
            for (j = l; j + 63 < i; j += 64) {
                _$1(abyte1, abyte0, 0, j, 64);
                _$1(abyte1);
            }

            k = 0;
        } else {
            j = 0;
        }
        _$1(_$2, abyte0, k, j, i - j);
    }

    private void _$1() {
        byte abyte0[] = new byte[8];
        _$1(abyte0, _$3, 8);
        int i = (int) (_$3[0] >>> 3) & 0x3f;
        int j = i >= 56 ? 120 - i : 56 - i;
        _$1(_$5, j);
        _$1(abyte0, 8);
        _$1(_$1, _$4, 16);
    }

    private void _$1(byte abyte0[], byte abyte1[], int i, int j, int k) {
        for (int l = 0; l < k; l++)
            abyte0[i + l] = abyte1[j + l];

    }

    private void _$1(byte abyte0[]) {
        long l = _$4[0];
        long l1 = _$4[1];
        long l2 = _$4[2];
        long l3 = _$4[3];
        long al[] = new long[16];
        _$1(al, abyte0, 64);
        l = _$4(l, l1, l2, l3, al[0], 7L, 0xd76aa478L);
        l3 = _$4(l3, l, l1, l2, al[1], 12L, 0xe8c7b756L);
        l2 = _$4(l2, l3, l, l1, al[2], 17L, 0x242070dbL);
        l1 = _$4(l1, l2, l3, l, al[3], 22L, 0xc1bdceeeL);
        l = _$4(l, l1, l2, l3, al[4], 7L, 0xf57c0fafL);
        l3 = _$4(l3, l, l1, l2, al[5], 12L, 0x4787c62aL);
        l2 = _$4(l2, l3, l, l1, al[6], 17L, 0xa8304613L);
        l1 = _$4(l1, l2, l3, l, al[7], 22L, 0xfd469501L);
        l = _$4(l, l1, l2, l3, al[8], 7L, 0x698098d8L);
        l3 = _$4(l3, l, l1, l2, al[9], 12L, 0x8b44f7afL);
        l2 = _$4(l2, l3, l, l1, al[10], 17L, 0xffff5bb1L);
        l1 = _$4(l1, l2, l3, l, al[11], 22L, 0x895cd7beL);
        l = _$4(l, l1, l2, l3, al[12], 7L, 0x6b901122L);
        l3 = _$4(l3, l, l1, l2, al[13], 12L, 0xfd987193L);
        l2 = _$4(l2, l3, l, l1, al[14], 17L, 0xa679438eL);
        l1 = _$4(l1, l2, l3, l, al[15], 22L, 0x49b40821L);
        l = _$3(l, l1, l2, l3, al[1], 5L, 0xf61e2562L);
        l3 = _$3(l3, l, l1, l2, al[6], 9L, 0xc040b340L);
        l2 = _$3(l2, l3, l, l1, al[11], 14L, 0x265e5a51L);
        l1 = _$3(l1, l2, l3, l, al[0], 20L, 0xe9b6c7aaL);
        l = _$3(l, l1, l2, l3, al[5], 5L, 0xd62f105dL);
        l3 = _$3(l3, l, l1, l2, al[10], 9L, 0x2441453L);
        l2 = _$3(l2, l3, l, l1, al[15], 14L, 0xd8a1e681L);
        l1 = _$3(l1, l2, l3, l, al[4], 20L, 0xe7d3fbc8L);
        l = _$3(l, l1, l2, l3, al[9], 5L, 0x21e1cde6L);
        l3 = _$3(l3, l, l1, l2, al[14], 9L, 0xc33707d6L);
        l2 = _$3(l2, l3, l, l1, al[3], 14L, 0xf4d50d87L);
        l1 = _$3(l1, l2, l3, l, al[8], 20L, 0x455a14edL);
        l = _$3(l, l1, l2, l3, al[13], 5L, 0xa9e3e905L);
        l3 = _$3(l3, l, l1, l2, al[2], 9L, 0xfcefa3f8L);
        l2 = _$3(l2, l3, l, l1, al[7], 14L, 0x676f02d9L);
        l1 = _$3(l1, l2, l3, l, al[12], 20L, 0x8d2a4c8aL);
        l = _$2(l, l1, l2, l3, al[5], 4L, 0xfffa3942L);
        l3 = _$2(l3, l, l1, l2, al[8], 11L, 0x8771f681L);
        l2 = _$2(l2, l3, l, l1, al[11], 16L, 0x6d9d6122L);
        l1 = _$2(l1, l2, l3, l, al[14], 23L, 0xfde5380cL);
        l = _$2(l, l1, l2, l3, al[1], 4L, 0xa4beea44L);
        l3 = _$2(l3, l, l1, l2, al[4], 11L, 0x4bdecfa9L);
        l2 = _$2(l2, l3, l, l1, al[7], 16L, 0xf6bb4b60L);
        l1 = _$2(l1, l2, l3, l, al[10], 23L, 0xbebfbc70L);
        l = _$2(l, l1, l2, l3, al[13], 4L, 0x289b7ec6L);
        l3 = _$2(l3, l, l1, l2, al[0], 11L, 0xeaa127faL);
        l2 = _$2(l2, l3, l, l1, al[3], 16L, 0xd4ef3085L);
        l1 = _$2(l1, l2, l3, l, al[6], 23L, 0x4881d05L);
        l = _$2(l, l1, l2, l3, al[9], 4L, 0xd9d4d039L);
        l3 = _$2(l3, l, l1, l2, al[12], 11L, 0xe6db99e5L);
        l2 = _$2(l2, l3, l, l1, al[15], 16L, 0x1fa27cf8L);
        l1 = _$2(l1, l2, l3, l, al[2], 23L, 0xc4ac5665L);
        l = _$1(l, l1, l2, l3, al[0], 6L, 0xf4292244L);
        l3 = _$1(l3, l, l1, l2, al[7], 10L, 0x432aff97L);
        l2 = _$1(l2, l3, l, l1, al[14], 15L, 0xab9423a7L);
        l1 = _$1(l1, l2, l3, l, al[5], 21L, 0xfc93a039L);
        l = _$1(l, l1, l2, l3, al[12], 6L, 0x655b59c3L);
        l3 = _$1(l3, l, l1, l2, al[3], 10L, 0x8f0ccc92L);
        l2 = _$1(l2, l3, l, l1, al[10], 15L, 0xffeff47dL);
        l1 = _$1(l1, l2, l3, l, al[1], 21L, 0x85845dd1L);
        l = _$1(l, l1, l2, l3, al[8], 6L, 0x6fa87e4fL);
        l3 = _$1(l3, l, l1, l2, al[15], 10L, 0xfe2ce6e0L);
        l2 = _$1(l2, l3, l, l1, al[6], 15L, 0xa3014314L);
        l1 = _$1(l1, l2, l3, l, al[13], 21L, 0x4e0811a1L);
        l = _$1(l, l1, l2, l3, al[4], 6L, 0xf7537e82L);
        l3 = _$1(l3, l, l1, l2, al[11], 10L, 0xbd3af235L);
        l2 = _$1(l2, l3, l, l1, al[2], 15L, 0x2ad7d2bbL);
        l1 = _$1(l1, l2, l3, l, al[9], 21L, 0xeb86d391L);
        _$4[0] += l;
        _$4[1] += l1;
        _$4[2] += l2;
        _$4[3] += l3;
    }

    private void _$1(byte abyte0[], long al[], int i) {
        int j = 0;
        for (int k = 0; k < i; k += 4) {
            abyte0[k] = (byte) (int) (al[j] & 255L);
            abyte0[k + 1] = (byte) (int) (al[j] >>> 8 & 255L);
            abyte0[k + 2] = (byte) (int) (al[j] >>> 16 & 255L);
            abyte0[k + 3] = (byte) (int) (al[j] >>> 24 & 255L);
            j++;
        }

    }

    private void _$1(long al[], byte abyte0[], int i) {
        int j = 0;
        for (int k = 0; k < i; k += 4) {
            al[j] = b2iu(abyte0[k]) | b2iu(abyte0[k + 1]) << 8 | b2iu(abyte0[k + 2]) << 16 | b2iu(abyte0[k + 3]) << 24;
            j++;
        }

    }

    public static long b2iu(byte byte0) {
        return byte0 >= 0 ? byte0 : byte0 & 0xff;
    }

    public static String byteHEX(byte byte0) {
        char ac2[] = new char[16];
        ac2[0] = '0';
        ac2[1] = '1';
        ac2[2] = '2';
        ac2[3] = '3';
        ac2[4] = '4';
        ac2[5] = '5';
        ac2[6] = '6';
        ac2[7] = '7';
        ac2[8] = '8';
        ac2[9] = '9';
        ac2[10] = 'A';
        ac2[11] = 'B';
        ac2[12] = 'C';
        ac2[13] = 'D';
        ac2[14] = 'E';
        ac2[15] = 'F';
        char ac1[];
        ac1 = new char[2];
        ac1[0] = ac2[byte0 >>> 4 & 0xf];
        ac1[1] = ac2[byte0 & 0xf];
        return new String(ac1);
    }

    static final int _$21 = 7;

    static final int _$20 = 12;

    static final int _$19 = 17;

    static final int _$18 = 22;

    static final int _$17 = 5;

    static final int _$16 = 9;

    static final int _$15 = 14;

    static final int _$14 = 20;

    static final int _$13 = 4;

    static final int _$12 = 11;

    static final int _$11 = 16;

    static final int _$10 = 23;

    static final int _$9 = 6;

    static final int _$8 = 10;

    static final int _$7 = 15;

    static final int _$6 = 21;

    static final byte _$5[];

    private final long _$4[];

    private final long _$3[];

    private final byte _$2[];

    public String digestHexStr;

    private final byte _$1[];

    static {
        byte abyte0[] = new byte[64];
        abyte0[0] = -128;
        abyte0[1] = 1;
        abyte0[2] = 0;
        abyte0[3] = 0;
        abyte0[4] = 0;
        abyte0[5] = 0;
        abyte0[6] = 0;
        abyte0[7] = 0;
        abyte0[8] = 0;
        abyte0[9] = 0;
        abyte0[10] = 0;
        abyte0[11] = 0;
        abyte0[12] = 0;
        abyte0[13] = 0;
        abyte0[14] = 0;
        abyte0[15] = 0;
        abyte0[16] = 0;
        abyte0[17] = 0;
        abyte0[18] = 0;
        abyte0[19] = 0;
        abyte0[20] = 0;
        abyte0[21] = 0;
        abyte0[22] = 0;
        abyte0[23] = 0;
        abyte0[24] = 0;
        abyte0[25] = 0;
        abyte0[26] = 0;
        abyte0[27] = 0;
        abyte0[28] = 0;
        abyte0[29] = 0;
        abyte0[30] = 0;
        abyte0[31] = 0;
        abyte0[32] = 0;
        abyte0[33] = 0;
        abyte0[34] = 0;
        abyte0[35] = 0;
        abyte0[36] = 0;
        abyte0[37] = 0;
        abyte0[38] = 0;
        abyte0[39] = 0;
        abyte0[40] = 0;
        abyte0[41] = 0;
        abyte0[42] = 0;
        abyte0[43] = 0;
        abyte0[44] = 0;
        abyte0[45] = 0;
        abyte0[46] = 0;
        abyte0[47] = 0;
        abyte0[48] = 0;
        abyte0[49] = 0;
        abyte0[50] = 0;
        abyte0[51] = 0;
        abyte0[52] = 0;
        abyte0[53] = 0;
        abyte0[54] = 0;
        abyte0[55] = 0;
        abyte0[56] = 0;
        abyte0[57] = 0;
        abyte0[58] = 0;
        abyte0[59] = 0;
        abyte0[60] = 0;
        abyte0[61] = 0;
        abyte0[62] = 0;
        abyte0[63] = 0;
        _$5 = abyte0;
    }
}
