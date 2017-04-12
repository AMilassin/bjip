/*
 * Global store references.
 * The application uses one (singleton) store instance.
 *
 * @flow
 */
import Store from './Store';
import TransportLayer from './TransportLayer';

export const transportLayer = new TransportLayer();
export const store = new Store(transportLayer);
