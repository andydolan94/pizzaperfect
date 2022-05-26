import { Pizza } from "./pizza";

export interface PizzaOrder {
	id: number;
	customerName: string;
	deliveryAddress: string;
	submitted: boolean;
	delivered: boolean;
	pizzas: Pizza[]
}

export interface CreatePizzaOrderInput {
	customerName: string;
	deliveryAddress: string;
	submitted: boolean;
	delivered: boolean;
}

export interface UpdatePizzaOrderInput {
	customerName: string;
	deliveryAddress: string;
	submitted: boolean;
	delivered: boolean;
}
