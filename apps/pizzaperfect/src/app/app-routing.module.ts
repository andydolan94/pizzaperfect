import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent, MakeOrderComponent, PizzaOrdersComponent } from '@pizzaperfect/features';

const routes: Routes = [
	{
		path: '',
		component: HomeComponent
	},
	{
		path: 'make-order/:id',
		component: MakeOrderComponent
	},
	{
		path: 'pizza-orders',
		component: PizzaOrdersComponent
	}
];

@NgModule({
	imports: [RouterModule.forRoot(routes)],
	exports: [RouterModule],
})
export class AppRoutingModule {}
