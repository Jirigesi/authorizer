/**
 * Authorizer
 *
 *  Copyright 2016 by Tjado Mäcke <tjado@maecke.de>
 *  Licensed under GNU General Public License 3.0.
 *
 *  Layout provided by Julian Wampfler <me@absturztaube.ch>
 */

package uci.tjado.authorizer;


public class UsbHidKbd_neo extends UsbHidKbd {

    public UsbHidKbd_neo() {

        kbdVal.put(null,				new byte[] {0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00} );

        //first layer
        kbdVal.put("^",                                 new byte[] {0x00, 0x00, 0x36, 0x00, 0x00, 0x00, 0x00, 0x00} );
        kbdVal.put("1",					new byte[] {0x00, 0x00, 0x1e, 0x00, 0x00, 0x00, 0x00, 0x00} );
        kbdVal.put("2",					new byte[] {0x00, 0x00, 0x1f, 0x00, 0x00, 0x00, 0x00, 0x00} );
        kbdVal.put("3",					new byte[] {0x00, 0x00, 0x20, 0x00, 0x00, 0x00, 0x00, 0x00} );
        kbdVal.put("4",					new byte[] {0x00, 0x00, 0x21, 0x00, 0x00, 0x00, 0x00, 0x00} );
        kbdVal.put("5",					new byte[] {0x00, 0x00, 0x22, 0x00, 0x00, 0x00, 0x00, 0x00} );
        kbdVal.put("6",					new byte[] {0x00, 0x00, 0x23, 0x00, 0x00, 0x00, 0x00, 0x00} );
        kbdVal.put("7",					new byte[] {0x00, 0x00, 0x24, 0x00, 0x00, 0x00, 0x00, 0x00} );
        kbdVal.put("8",					new byte[] {0x00, 0x00, 0x25, 0x00, 0x00, 0x00, 0x00, 0x00} );
        kbdVal.put("9",					new byte[] {0x00, 0x00, 0x26, 0x00, 0x00, 0x00, 0x00, 0x00} );
        kbdVal.put("0",					new byte[] {0x00, 0x00, 0x27, 0x00, 0x00, 0x00, 0x00, 0x00} );
        kbdVal.put("-",                                 new byte[] {0x00, 0x00, 0x2d, 0x00, 0x00, 0x00, 0x00, 0x00} );
        kbdVal.put("`",                                 new byte[] {0x00, 0x00, 0x2e, 0x00, 0x00, 0x00, 0x00, 0x00} );
        kbdVal.put("bckspc",			        new byte[] {0x00, 0x00, 0x2a, 0x00, 0x00, 0x00, 0x00, 0x00} );
        kbdVal.put("backspace",			        new byte[] {0x00, 0x00, 0x2a, 0x00, 0x00, 0x00, 0x00, 0x00} );

        kbdVal.put("tab",				new byte[] {0x00, 0x00, 0x2b, 0x00, 0x00, 0x00, 0x00, 0x00} );
        kbdVal.put("tabulator",				new byte[] {0x00, 0x00, 0x2b, 0x00, 0x00, 0x00, 0x00, 0x00} );
        kbdVal.put("\t",				new byte[] {0x00, 0x00, 0x2b, 0x00, 0x00, 0x00, 0x00, 0x00} );
        kbdVal.put("x",                                 new byte[] {0x00, 0x00, 0x14, 0x00, 0x00, 0x00, 0x00, 0x00} );
        kbdVal.put("v",                                 new byte[] {0x00, 0x00, 0x1a, 0x00, 0x00, 0x00, 0x00, 0x00} );
        kbdVal.put("l",                                 new byte[] {0x00, 0x00, 0x08, 0x00, 0x00, 0x00, 0x00, 0x00} );
        kbdVal.put("c",                                 new byte[] {0x00, 0x00, 0x15, 0x00, 0x00, 0x00, 0x00, 0x00} );
        kbdVal.put("w",                                 new byte[] {0x00, 0x00, 0x17, 0x00, 0x00, 0x00, 0x00, 0x00} );
        kbdVal.put("k",                                 new byte[] {0x00, 0x00, 0x1c, 0x00, 0x00, 0x00, 0x00, 0x00} );
        kbdVal.put("h",                                 new byte[] {0x00, 0x00, 0x18, 0x00, 0x00, 0x00, 0x00, 0x00} );
        kbdVal.put("g",                                 new byte[] {0x00, 0x00, 0x0c, 0x00, 0x00, 0x00, 0x00, 0x00} );
        kbdVal.put("f",                                 new byte[] {0x00, 0x00, 0x12, 0x00, 0x00, 0x00, 0x00, 0x00} );
        kbdVal.put("q",                                 new byte[] {0x00, 0x00, 0x13, 0x00, 0x00, 0x00, 0x00, 0x00} );
        kbdVal.put("ß",                                 new byte[] {0x00, 0x00, 0x2f, 0x00, 0x00, 0x00, 0x00, 0x00} );
        kbdVal.put("´",                                 new byte[] {0x00, 0x00, 0x30, 0x00, 0x00, 0x00, 0x00, 0x00} );

        //kbdVal.put("mod3",                            new byte[] {0x00, 0x00, 0x39, 0x00, 0x00, 0x00, 0x00, 0x00} );
        kbdVal.put("u",                                 new byte[] {0x00, 0x00, 0x04, 0x00, 0x00, 0x00, 0x00, 0x00} );
        kbdVal.put("i",                                 new byte[] {0x00, 0x00, 0x16, 0x00, 0x00, 0x00, 0x00, 0x00} );
        kbdVal.put("a",                                 new byte[] {0x00, 0x00, 0x07, 0x00, 0x00, 0x00, 0x00, 0x00} );
        kbdVal.put("e",                                 new byte[] {0x00, 0x00, 0x09, 0x00, 0x00, 0x00, 0x00, 0x00} );
        kbdVal.put("o",                                 new byte[] {0x00, 0x00, 0x0a, 0x00, 0x00, 0x00, 0x00, 0x00} );
        kbdVal.put("s",                                 new byte[] {0x00, 0x00, 0x0b, 0x00, 0x00, 0x00, 0x00, 0x00} );
        kbdVal.put("n",                                 new byte[] {0x00, 0x00, 0x0d, 0x00, 0x00, 0x00, 0x00, 0x00} );
        kbdVal.put("r",                                 new byte[] {0x00, 0x00, 0x0e, 0x00, 0x00, 0x00, 0x00, 0x00} );
        kbdVal.put("t",                                 new byte[] {0x00, 0x00, 0x0f, 0x00, 0x00, 0x00, 0x00, 0x00} );
        kbdVal.put("d",                                 new byte[] {0x00, 0x00, 0x33, 0x00, 0x00, 0x00, 0x00, 0x00} );
        kbdVal.put("y",                                 new byte[] {0x00, 0x00, 0x34, 0x00, 0x00, 0x00, 0x00, 0x00} );
        //kbdVal.put("mod3",                            new byte[] {0x00, 0x00, 0x32, 0x00, 0x00, 0x00, 0x00, 0x00} );
        kbdVal.put("return",			        new byte[] {0x00, 0x00, 0x28, 0x00, 0x00, 0x00, 0x00, 0x00} );
        kbdVal.put("enter",				new byte[] {0x00, 0x00, 0x28, 0x00, 0x00, 0x00, 0x00, 0x00} );
        kbdVal.put("\n",                                new byte[] {0x00, 0x00, 0x28, 0x00, 0x00, 0x00, 0x00, 0x00} );

        //kbdVal.put("mod2",                            new byte[] {0x00, 0x00, 0xe1, 0x00, 0x00, 0x00, 0x00, 0x00} );
        //kbdVal.put("mod4",                            new byte[] {0x00, 0x00, 0x64, 0x00, 0x00, 0x00, 0x00, 0x00} );
        kbdVal.put("ü",                                 new byte[] {0x00, 0x00, 0x1d, 0x00, 0x00, 0x00, 0x00, 0x00} );
        kbdVal.put("ö",                                 new byte[] {0x00, 0x00, 0x1b, 0x00, 0x00, 0x00, 0x00, 0x00} );
        kbdVal.put("ä",                                 new byte[] {0x00, 0x00, 0x06, 0x00, 0x00, 0x00, 0x00, 0x00} );
        kbdVal.put("p",                                 new byte[] {0x00, 0x00, 0x19, 0x00, 0x00, 0x00, 0x00, 0x00} );
        kbdVal.put("z",                                 new byte[] {0x00, 0x00, 0x05, 0x00, 0x00, 0x00, 0x00, 0x00} );
        kbdVal.put("b",                                 new byte[] {0x00, 0x00, 0x11, 0x00, 0x00, 0x00, 0x00, 0x00} );
        kbdVal.put("m",                                 new byte[] {0x00, 0x00, 0x10, 0x00, 0x00, 0x00, 0x00, 0x00} );
        kbdVal.put(",",                                 new byte[] {0x00, 0x00, 0x36, 0x00, 0x00, 0x00, 0x00, 0x00} );
        kbdVal.put(".",                                 new byte[] {0x00, 0x00, 0x37, 0x00, 0x00, 0x00, 0x00, 0x00} );
        kbdVal.put("j",                                 new byte[] {0x00, 0x00, 0x38, 0x00, 0x00, 0x00, 0x00, 0x00} );
        //kbdVal.put("mod2",                            new byte[] {0x00, 0x00, 0xe5, 0x00, 0x00, 0x00, 0x00, 0x00} );

        //kbdVal.put("LCtrl",                           new byte[] {0x00, 0x00, 0xe0, 0x00, 0x00, 0x00, 0x00, 0x00} );
        //kbdVal.put("LGui",                            new byte[] {0x00, 0x00, 0xe3, 0x00, 0x00, 0x00, 0x00, 0x00} );
        //kbdVal.put("LAlt",                            new byte[] {0x00, 0x00, 0xe2, 0x00, 0x00, 0x00, 0x00, 0x00} );
        kbdVal.put(" ",					new byte[] {0x00, 0x00, 0x2c, 0x00, 0x00, 0x00, 0x00, 0x00} );
        //kbdVal.put("mod4",                            new byte[] {0x00, 0x00, 0xe6, 0x00, 0x00, 0x00, 0x00, 0x00} );
        //kbdVal.put("RGui",                            new byte[] {0x00, 0x00, 0xe7, 0x00, 0x00, 0x00, 0x00, 0x00} );
        //kbdVal.put("RCtrl",                           new byte[] {0x00, 0x00, 0xe4, 0x00, 0x00, 0x00, 0x00, 0x00} );

        //second layer (with mod2 better known as shift)
        kbdVal.put("ˇ",                                 new byte[] {0x02, 0x00, 0x36, 0x00, 0x00, 0x00, 0x00, 0x00} );
        kbdVal.put("°",					new byte[] {0x02, 0x00, 0x1e, 0x00, 0x00, 0x00, 0x00, 0x00} );
        kbdVal.put("§",					new byte[] {0x02, 0x00, 0x1f, 0x00, 0x00, 0x00, 0x00, 0x00} );
        kbdVal.put("ℓ",					new byte[] {0x02, 0x00, 0x20, 0x00, 0x00, 0x00, 0x00, 0x00} );
        kbdVal.put("»",					new byte[] {0x02, 0x00, 0x21, 0x00, 0x00, 0x00, 0x00, 0x00} );
        kbdVal.put("«",					new byte[] {0x02, 0x00, 0x22, 0x00, 0x00, 0x00, 0x00, 0x00} );
        kbdVal.put("$",					new byte[] {0x02, 0x00, 0x23, 0x00, 0x00, 0x00, 0x00, 0x00} );
        kbdVal.put("€",					new byte[] {0x02, 0x00, 0x24, 0x00, 0x00, 0x00, 0x00, 0x00} );
        kbdVal.put("„",					new byte[] {0x02, 0x00, 0x25, 0x00, 0x00, 0x00, 0x00, 0x00} );
        kbdVal.put("“",					new byte[] {0x02, 0x00, 0x26, 0x00, 0x00, 0x00, 0x00, 0x00} );
        kbdVal.put("”",					new byte[] {0x02, 0x00, 0x27, 0x00, 0x00, 0x00, 0x00, 0x00} );
        kbdVal.put("—",                                 new byte[] {0x02, 0x00, 0x2d, 0x00, 0x00, 0x00, 0x00, 0x00} );
        kbdVal.put("̧",                                  new byte[] {0x02, 0x00, 0x2e, 0x00, 0x00, 0x00, 0x00, 0x00} );
        kbdVal.put("bckspc",			        new byte[] {0x02, 0x00, 0x2a, 0x00, 0x00, 0x00, 0x00, 0x00} );
        kbdVal.put("backspace",			        new byte[] {0x02, 0x00, 0x2a, 0x00, 0x00, 0x00, 0x00, 0x00} );

        kbdVal.put("backtab",				new byte[] {0x02, 0x00, 0x2b, 0x00, 0x00, 0x00, 0x00, 0x00} );
        kbdVal.put("backtabulator",			new byte[] {0x02, 0x00, 0x2b, 0x00, 0x00, 0x00, 0x00, 0x00} );
        //kbdVal.put("\t",				new byte[] {0x02, 0x00, 0x2b, 0x00, 0x00, 0x00, 0x00, 0x00} );
        kbdVal.put("X",                                 new byte[] {0x02, 0x00, 0x14, 0x00, 0x00, 0x00, 0x00, 0x00} );
        kbdVal.put("V",                                 new byte[] {0x02, 0x00, 0x1a, 0x00, 0x00, 0x00, 0x00, 0x00} );
        kbdVal.put("L",                                 new byte[] {0x02, 0x00, 0x08, 0x00, 0x00, 0x00, 0x00, 0x00} );
        kbdVal.put("C",                                 new byte[] {0x02, 0x00, 0x15, 0x00, 0x00, 0x00, 0x00, 0x00} );
        kbdVal.put("W",                                 new byte[] {0x02, 0x00, 0x17, 0x00, 0x00, 0x00, 0x00, 0x00} );
        kbdVal.put("K",                                 new byte[] {0x02, 0x00, 0x1c, 0x00, 0x00, 0x00, 0x00, 0x00} );
        kbdVal.put("H",                                 new byte[] {0x02, 0x00, 0x18, 0x00, 0x00, 0x00, 0x00, 0x00} );
        kbdVal.put("G",                                 new byte[] {0x02, 0x00, 0x0c, 0x00, 0x00, 0x00, 0x00, 0x00} );
        kbdVal.put("F",                                 new byte[] {0x02, 0x00, 0x12, 0x00, 0x00, 0x00, 0x00, 0x00} );
        kbdVal.put("Q",                                 new byte[] {0x02, 0x00, 0x13, 0x00, 0x00, 0x00, 0x00, 0x00} );
        kbdVal.put("ẞ",                                 new byte[] {0x02, 0x00, 0x2f, 0x00, 0x00, 0x00, 0x00, 0x00} );
        kbdVal.put("~",                                 new byte[] {0x02, 0x00, 0x30, 0x00, 0x00, 0x00, 0x00, 0x00} );

        //kbdVal.put("mod3",                            new byte[] {0x00, 0x00, 0x39, 0x00, 0x00, 0x00, 0x00, 0x00} );
        kbdVal.put("U",                                 new byte[] {0x02, 0x00, 0x04, 0x00, 0x00, 0x00, 0x00, 0x00} );
        kbdVal.put("I",                                 new byte[] {0x02, 0x00, 0x16, 0x00, 0x00, 0x00, 0x00, 0x00} );
        kbdVal.put("A",                                 new byte[] {0x02, 0x00, 0x07, 0x00, 0x00, 0x00, 0x00, 0x00} );
        kbdVal.put("E",                                 new byte[] {0x02, 0x00, 0x09, 0x00, 0x00, 0x00, 0x00, 0x00} );
        kbdVal.put("O",                                 new byte[] {0x02, 0x00, 0x0a, 0x00, 0x00, 0x00, 0x00, 0x00} );
        kbdVal.put("S",                                 new byte[] {0x02, 0x00, 0x0b, 0x00, 0x00, 0x00, 0x00, 0x00} );
        kbdVal.put("N",                                 new byte[] {0x02, 0x00, 0x0d, 0x00, 0x00, 0x00, 0x00, 0x00} );
        kbdVal.put("R",                                 new byte[] {0x02, 0x00, 0x0e, 0x00, 0x00, 0x00, 0x00, 0x00} );
        kbdVal.put("T",                                 new byte[] {0x02, 0x00, 0x0f, 0x00, 0x00, 0x00, 0x00, 0x00} );
        kbdVal.put("D",                                 new byte[] {0x02, 0x00, 0x33, 0x00, 0x00, 0x00, 0x00, 0x00} );
        kbdVal.put("Y",                                 new byte[] {0x02, 0x00, 0x34, 0x00, 0x00, 0x00, 0x00, 0x00} );
        //kbdVal.put("mod3",                            new byte[] {0x00, 0x00, 0x32, 0x00, 0x00, 0x00, 0x00, 0x00} );
        kbdVal.put("return",			        new byte[] {0x02, 0x00, 0x28, 0x00, 0x00, 0x00, 0x00, 0x00} );
        kbdVal.put("enter",				new byte[] {0x02, 0x00, 0x28, 0x00, 0x00, 0x00, 0x00, 0x00} );
        kbdVal.put("\n",                                new byte[] {0x02, 0x00, 0x28, 0x00, 0x00, 0x00, 0x00, 0x00} );

        //kbdVal.put("mod2",                            new byte[] {0x00, 0x00, 0xe1, 0x00, 0x00, 0x00, 0x00, 0x00} );
        //kbdVal.put("mod4",                            new byte[] {0x00, 0x00, 0x64, 0x00, 0x00, 0x00, 0x00, 0x00} );
        kbdVal.put("Ü",                                 new byte[] {0x02, 0x00, 0x1d, 0x00, 0x00, 0x00, 0x00, 0x00} );
        kbdVal.put("Ö",                                 new byte[] {0x02, 0x00, 0x1b, 0x00, 0x00, 0x00, 0x00, 0x00} );
        kbdVal.put("Ä",                                 new byte[] {0x02, 0x00, 0x06, 0x00, 0x00, 0x00, 0x00, 0x00} );
        kbdVal.put("P",                                 new byte[] {0x02, 0x00, 0x19, 0x00, 0x00, 0x00, 0x00, 0x00} );
        kbdVal.put("Z",                                 new byte[] {0x02, 0x00, 0x05, 0x00, 0x00, 0x00, 0x00, 0x00} );
        kbdVal.put("B",                                 new byte[] {0x02, 0x00, 0x11, 0x00, 0x00, 0x00, 0x00, 0x00} );
        kbdVal.put("M",                                 new byte[] {0x02, 0x00, 0x10, 0x00, 0x00, 0x00, 0x00, 0x00} );
        kbdVal.put("–",                                 new byte[] {0x02, 0x00, 0x36, 0x00, 0x00, 0x00, 0x00, 0x00} );
        kbdVal.put("·",                                 new byte[] {0x02, 0x00, 0x37, 0x00, 0x00, 0x00, 0x00, 0x00} );
        kbdVal.put("J",                                 new byte[] {0x02, 0x00, 0x38, 0x00, 0x00, 0x00, 0x00, 0x00} );
        //kbdVal.put("mod2",                            new byte[] {0x00, 0x00, 0xe5, 0x00, 0x00, 0x00, 0x00, 0x00} );

        //kbdVal.put("LCtrl",                           new byte[] {0x00, 0x00, 0xe0, 0x00, 0x00, 0x00, 0x00, 0x00} );
        //kbdVal.put("LGui",                            new byte[] {0x00, 0x00, 0xe3, 0x00, 0x00, 0x00, 0x00, 0x00} );
        //kbdVal.put("LAlt",                            new byte[] {0x00, 0x00, 0xe2, 0x00, 0x00, 0x00, 0x00, 0x00} );
        kbdVal.put(" ",					new byte[] {0x02, 0x00, 0x2c, 0x00, 0x00, 0x00, 0x00, 0x00} );
        //kbdVal.put("mod4",                            new byte[] {0x00, 0x00, 0xe6, 0x00, 0x00, 0x00, 0x00, 0x00} );
        //kbdVal.put("RGui",                            new byte[] {0x00, 0x00, 0xe7, 0x00, 0x00, 0x00, 0x00, 0x00} );
        //kbdVal.put("RCtrl",                           new byte[] {0x00, 0x00, 0xe4, 0x00, 0x00, 0x00, 0x00, 0x00} );

        //third layer (with mod3)
        kbdVal.put("↻",                                 new byte[] {0x40, 0x00, 0x36, 0x00, 0x00, 0x00, 0x00, 0x00} );
        kbdVal.put("¹",					new byte[] {0x40, 0x00, 0x1e, 0x00, 0x00, 0x00, 0x00, 0x00} );
        kbdVal.put("²",					new byte[] {0x40, 0x00, 0x1f, 0x00, 0x00, 0x00, 0x00, 0x00} );
        kbdVal.put("³",					new byte[] {0x40, 0x00, 0x20, 0x00, 0x00, 0x00, 0x00, 0x00} );
        kbdVal.put("›",					new byte[] {0x40, 0x00, 0x21, 0x00, 0x00, 0x00, 0x00, 0x00} );
        kbdVal.put("‹",					new byte[] {0x40, 0x00, 0x22, 0x00, 0x00, 0x00, 0x00, 0x00} );
        kbdVal.put("¢",					new byte[] {0x40, 0x00, 0x23, 0x00, 0x00, 0x00, 0x00, 0x00} );
        kbdVal.put("¥",					new byte[] {0x40, 0x00, 0x24, 0x00, 0x00, 0x00, 0x00, 0x00} );
        kbdVal.put("‚",					new byte[] {0x40, 0x00, 0x25, 0x00, 0x00, 0x00, 0x00, 0x00} );
        kbdVal.put("‘",					new byte[] {0x40, 0x00, 0x26, 0x00, 0x00, 0x00, 0x00, 0x00} );
        kbdVal.put("’",					new byte[] {0x40, 0x00, 0x27, 0x00, 0x00, 0x00, 0x00, 0x00} );
        //kbdVal.put("unused",                          new byte[] {0x02, 0x00, 0x2d, 0x00, 0x00, 0x00, 0x00, 0x00} );
        kbdVal.put(" ̊",                                 new byte[] {0x40, 0x00, 0x2e, 0x00, 0x00, 0x00, 0x00, 0x00} );
        //kbdVal.put("bckspc",			        new byte[] {0x02, 0x00, 0x2a, 0x00, 0x00, 0x00, 0x00, 0x00} );
        //kbdVal.put("backspace",			new byte[] {0x02, 0x00, 0x2a, 0x00, 0x00, 0x00, 0x00, 0x00} );

        kbdVal.put("♫",				        new byte[] {0x40, 0x00, 0x2b, 0x00, 0x00, 0x00, 0x00, 0x00} );
        kbdVal.put("᳟",                                  new byte[] {0x40, 0x00, 0x14, 0x00, 0x00, 0x00, 0x00, 0x00} );
        kbdVal.put("_",                                 new byte[] {0x40, 0x00, 0x1a, 0x00, 0x00, 0x00, 0x00, 0x00} );
        kbdVal.put("[",                                 new byte[] {0x40, 0x00, 0x08, 0x00, 0x00, 0x00, 0x00, 0x00} );
        kbdVal.put("]",                                 new byte[] {0x40, 0x00, 0x15, 0x00, 0x00, 0x00, 0x00, 0x00} );
        kbdVal.put("^",                                 new byte[] {0x40, 0x00, 0x17, 0x00, 0x00, 0x00, 0x00, 0x00} );
        kbdVal.put("!",                                 new byte[] {0x40, 0x00, 0x1c, 0x00, 0x00, 0x00, 0x00, 0x00} );
        kbdVal.put("<",                                 new byte[] {0x40, 0x00, 0x18, 0x00, 0x00, 0x00, 0x00, 0x00} );
        kbdVal.put(">",                                 new byte[] {0x40, 0x00, 0x0c, 0x00, 0x00, 0x00, 0x00, 0x00} );
        kbdVal.put("=",                                 new byte[] {0x40, 0x00, 0x12, 0x00, 0x00, 0x00, 0x00, 0x00} );
        kbdVal.put("&",                                 new byte[] {0x40, 0x00, 0x13, 0x00, 0x00, 0x00, 0x00, 0x00} );
        //kbdVal.put("s",                               new byte[] {0x02, 0x00, 0x2f, 0x00, 0x00, 0x00, 0x00, 0x00} ); //cant find the char for this
        //kbdVal.put("~",                               new byte[] {0x02, 0x00, 0x30, 0x00, 0x00, 0x00, 0x00, 0x00} ); //and for this one too

        //kbdVal.put("mod3",                            new byte[] {0x00, 0x00, 0x39, 0x00, 0x00, 0x00, 0x00, 0x00} );
        kbdVal.put("\\",                                new byte[] {0x02, 0x00, 0x04, 0x00, 0x00, 0x00, 0x00, 0x00} );
        kbdVal.put("/",                                 new byte[] {0x40, 0x00, 0x16, 0x00, 0x00, 0x00, 0x00, 0x00} );
        kbdVal.put("{",                                 new byte[] {0x40, 0x00, 0x07, 0x00, 0x00, 0x00, 0x00, 0x00} );
        kbdVal.put("}",                                 new byte[] {0x40, 0x00, 0x09, 0x00, 0x00, 0x00, 0x00, 0x00} );
        kbdVal.put("*",                                 new byte[] {0x40, 0x00, 0x0a, 0x00, 0x00, 0x00, 0x00, 0x00} );
        kbdVal.put("?",                                 new byte[] {0x40, 0x00, 0x0b, 0x00, 0x00, 0x00, 0x00, 0x00} );
        kbdVal.put("(",                                 new byte[] {0x40, 0x00, 0x0d, 0x00, 0x00, 0x00, 0x00, 0x00} );
        kbdVal.put(")",                                 new byte[] {0x40, 0x00, 0x0e, 0x00, 0x00, 0x00, 0x00, 0x00} );
        kbdVal.put("-",                                 new byte[] {0x40, 0x00, 0x0f, 0x00, 0x00, 0x00, 0x00, 0x00} );
        kbdVal.put(":",                                 new byte[] {0x40, 0x00, 0x33, 0x00, 0x00, 0x00, 0x00, 0x00} );
        kbdVal.put("@",                                 new byte[] {0x40, 0x00, 0x34, 0x00, 0x00, 0x00, 0x00, 0x00} );
        //kbdVal.put("mod3",                            new byte[] {0x40, 0x00, 0x32, 0x00, 0x00, 0x00, 0x00, 0x00} );

        //kbdVal.put("mod2",                            new byte[] {0x40, 0x00, 0xe1, 0x00, 0x00, 0x00, 0x00, 0x00} );
        //kbdVal.put("mod4",                            new byte[] {0x40, 0x00, 0x64, 0x00, 0x00, 0x00, 0x00, 0x00} );
        kbdVal.put("#",                                 new byte[] {0x40, 0x00, 0x1d, 0x00, 0x00, 0x00, 0x00, 0x00} );
        kbdVal.put("$",                                 new byte[] {0x40, 0x00, 0x1b, 0x00, 0x00, 0x00, 0x00, 0x00} );
        kbdVal.put("|",                                 new byte[] {0x40, 0x00, 0x06, 0x00, 0x00, 0x00, 0x00, 0x00} );
        kbdVal.put("~",                                 new byte[] {0x40, 0x00, 0x19, 0x00, 0x00, 0x00, 0x00, 0x00} );
        kbdVal.put("`",                                 new byte[] {0x40, 0x00, 0x05, 0x00, 0x00, 0x00, 0x00, 0x00} );
        kbdVal.put("+",                                 new byte[] {0x40, 0x00, 0x11, 0x00, 0x00, 0x00, 0x00, 0x00} );
        kbdVal.put("%",                                 new byte[] {0x40, 0x00, 0x10, 0x00, 0x00, 0x00, 0x00, 0x00} );
        kbdVal.put("\"",                                new byte[] {0x40, 0x00, 0x36, 0x00, 0x00, 0x00, 0x00, 0x00} );
        kbdVal.put("'",                                 new byte[] {0x40, 0x00, 0x37, 0x00, 0x00, 0x00, 0x00, 0x00} );
        kbdVal.put(";",                                 new byte[] {0x40, 0x00, 0x38, 0x00, 0x00, 0x00, 0x00, 0x00} );
        //kbdVal.put("mod2",                            new byte[] {0x00, 0x00, 0xe5, 0x00, 0x00, 0x00, 0x00, 0x00} );

        //kbdVal.put("LCtrl",                           new byte[] {0x00, 0x00, 0xe0, 0x00, 0x00, 0x00, 0x00, 0x00} );
        //kbdVal.put("LGui",                            new byte[] {0x00, 0x00, 0xe3, 0x00, 0x00, 0x00, 0x00, 0x00} );
        //kbdVal.put("LAlt",                            new byte[] {0x00, 0x00, 0xe2, 0x00, 0x00, 0x00, 0x00, 0x00} );
        //kbdVal.put(" ",				new byte[] {0x02, 0x00, 0x2c, 0x00, 0x00, 0x00, 0x00, 0x00} );
        //kbdVal.put("mod4",                            new byte[] {0x00, 0x00, 0xe6, 0x00, 0x00, 0x00, 0x00, 0x00} );
        //kbdVal.put("RGui",                            new byte[] {0x00, 0x00, 0xe7, 0x00, 0x00, 0x00, 0x00, 0x00} );
        //kbdVal.put("RCtrl",                           new byte[] {0x00, 0x00, 0xe4, 0x00, 0x00, 0x00, 0x00, 0x00} );

        //fourth, fifth and sixth layer are left out because there are a bunch of characters not commonly used in passwords

        kbdVal.put("esc",				new byte[] {0x00, 0x00, 0x29, 0x00, 0x00, 0x00, 0x00, 0x00} );
        kbdVal.put("escape",			        new byte[] {0x00, 0x00, 0x29, 0x00, 0x00, 0x00, 0x00, 0x00} );
    }

}
