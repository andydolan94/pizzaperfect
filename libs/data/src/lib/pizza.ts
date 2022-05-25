export interface Pizza {
	id: number;
	topping: string;
	base: string;
	size: string;
	note: string;
	pizzaOrderId: number;
}

export interface CreatePizzaInput {
	topping: string;
	base: string;
	size: string;
	note: string;
	pizzaOrderId: number;
}

export interface UpdatePizzaInput {
	topping: string;
	base: string;
	size: string;
	note: string;
	pizzaOrderId: number;
}
