/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view.dialog;

import view.main.Main;

/**
 *
 * @author nguyenth28
 */
public class ShowMessageSuccessful {

    public static boolean showSuccessful(String message) {
        MessageSuccessful obj = new MessageSuccessful(Main.getFrames()[0], true);
        obj.showMessageSuccess(message);
        return obj.isOk();
    }
}
