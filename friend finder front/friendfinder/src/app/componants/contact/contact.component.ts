import { Component, OnInit } from '@angular/core';
import { ContactService } from '../../service/contact/contact.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-contact',
  templateUrl: './contact.component.html',
  styleUrls: ['./contact.component.css']
})
export class ContactComponent implements OnInit {

  messageAr = '';
  messageEn = '';

  successMessage = '';
  showSuccess = false;

  constructor(
    private contactService: ContactService,
    private router: Router
  ) { }

  ngOnInit(): void {
  }

  doContact(name: string, email: string, phone: string, message: string) {

    if (!this.validateInput(name, email, phone, message)) {
      setTimeout(() => {
        this.messageAr = '';
        this.messageEn = '';
      }, 3000);
      return;
    }

    this.contactService.doContact(name, email, phone, message).subscribe({
      next: () => {

        this.showSuccess = true;
        this.successMessage = 'Your message has been sent successfully.';

        setTimeout(() => {
          this.showSuccess = false;
          this.successMessage = '';
          this.router.navigateByUrl('/mainPage');
        }, 3000);
      },
      error: () => {
        this.messageAr = 'حدث خطأ أثناء إرسال الرسالة';
        this.messageEn = 'Failed to send message';
      }
    });
  }

  validateInput(name: string, email: string, phone: string, message: string): boolean {

    if (!name) {
      this.messageAr = 'الاسم لا يجب أن يكون فارغ';
      this.messageEn = 'Username must not be empty';
      return false;
    }

    if (!email) {
      this.messageAr = 'البريد الإلكتروني لا يجب أن يكون فارغ';
      this.messageEn = 'Email must not be empty';
      return false;
    }

    if (!phone) {
      this.messageAr = 'رقم الهاتف لا يجب أن يكون فارغ';
      this.messageEn = 'Phone number must not be empty';
      return false;
    }

    if (!message) {
      this.messageAr = 'الرسالة لا يجب أن تكون فارغة';
      this.messageEn = 'Message must not be empty';
      return false;
    }

    return true;
  }

}
