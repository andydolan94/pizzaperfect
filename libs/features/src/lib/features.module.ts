import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HomeComponent } from './home/home.component';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { ReactiveFormsModule } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { BrowserModule } from '@angular/platform-browser';
import { PizzaOrdersComponent } from './pizza-orders/pizza-orders.component';
import { PizzaOrderComponent } from './pizza-orders/pizza-order/pizza-order.component';
import { RouterModule } from '@angular/router';

@NgModule({
	declarations: [HomeComponent, PizzaOrdersComponent, PizzaOrderComponent],
	imports: [
		CommonModule,
		BrowserModule,
		ReactiveFormsModule,
		MatFormFieldModule,
		MatInputModule,
		MatButtonModule,
		MatCardModule,
		RouterModule
	],
	exports: [HomeComponent, PizzaOrdersComponent, PizzaOrderComponent],
})
export class FeaturesModule {}
