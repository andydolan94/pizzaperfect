import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { NxWelcomeComponent } from './nx-welcome.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AppRoutingModule } from './app-routing.module';
import { FeaturesModule } from '@pizzaperfect/features';
import { HttpClientModule } from '@angular/common/http';

@NgModule({
	declarations: [AppComponent, NxWelcomeComponent],
	imports: [
		AppRoutingModule,
		BrowserModule,
		BrowserAnimationsModule,
		HttpClientModule,
		FeaturesModule,
	],
	providers: [],
	bootstrap: [AppComponent],
})
export class AppModule {}
