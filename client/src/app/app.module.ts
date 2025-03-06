import { HttpClientModule, provideHttpClient, withInterceptorsFromDi } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { provideAnimationsAsync } from '@angular/platform-browser/animations/async';
import { AppComponent } from './app.component';
import { MaterialModule } from './material.module';

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,ReactiveFormsModule,MaterialModule
  ],
  providers: [provideHttpClient(withInterceptorsFromDi()), provideAnimationsAsync()],
  bootstrap: [AppComponent]
})
export class AppModule { }