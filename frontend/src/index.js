import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from './App';
import reportWebVitals from './reportWebVitals';

import userReducer from './reducers/user.reducer'
import { Provider } from 'react-redux';
import { createStore, combineReducers } from 'redux';
import routeReducer from './reducers/route.reducer';

const rootReducer = combineReducers({
  rUser: userReducer,
  rRoute: routeReducer,
})
const store = createStore(rootReducer);

ReactDOM.render(
  <Provider store={store}> 
    <React.StrictMode>
      <App />
    </React.StrictMode>
   </Provider>,
  document.getElementById('root')
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
