import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import {
  FormControl,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';

@Component({
  selector: 'app-chat-message-input',
  standalone: true,
  imports: [ReactiveFormsModule],
  templateUrl: './message-input.component.html',
  styleUrl: './message-input.component.scss',
})
export class ChatMessageInputComponent implements OnInit {
  messageForm!: FormGroup;
  @Output() sendMessage = new EventEmitter<string>();

  ngOnInit(): void {
    this.messageForm = new FormGroup({
      answer: new FormControl('', [
        Validators.required,
        Validators.minLength(1),
      ]),
    });
  }

  onSendMessage(): void {
    if (!this.messageForm.valid) {
      return;
    }

    const content = this.messageForm.value.answer;

    this.messageForm.reset();
    this.sendMessage.emit(content);
  }
}
