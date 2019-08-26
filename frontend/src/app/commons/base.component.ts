import {ToastController} from "@ionic/angular";

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
