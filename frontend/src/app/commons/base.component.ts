import {ToastController} from '@ionic/angular';

/**
 * Da ich redundanten Code vermeiden will, habe ich die Basis-Funktionalität in die Base-Komponente getan.
 *
 * @author: Petrovic Boban, boban_96@hotmail.de
 */
export class BaseComponent {
    constructor(protected toastController: ToastController) {
    }

    async presentToast(msg: string) {
        const toast = await this.toastController.create({
            message: msg,
            duration: 2000
        });
        await toast.present();
    }
}
