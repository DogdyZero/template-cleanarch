package com.fiap.techchallenge.presenters.interfaces;

public interface ApplicationPresenter<T extends ModelView> {
	T toModelView(Object object);
}
