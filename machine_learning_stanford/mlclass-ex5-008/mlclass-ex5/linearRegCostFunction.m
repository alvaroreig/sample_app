function [J, grad] = linearRegCostFunction(X, y, theta, lambda)
%LINEARREGCOSTFUNCTION Compute cost and gradient for regularized linear 
%regression with multiple variables
%   [J, grad] = LINEARREGCOSTFUNCTION(X, y, theta, lambda) computes the 
%   cost of using theta as the parameter for linear regression to fit the 
%   data points in X and y. Returns the cost in J and the gradient in grad

% Initialize some useful values
m = length(y); % number of training examples

% You need to return the following variables correctly 
J = 0;
grad = zeros(size(theta));

% ====================== YOUR CODE HERE ======================
% Instructions: Compute the cost and gradient of regularized linear 
%               regression for a particular choice of theta.
%
%               You should set J to the cost and grad to the gradient.
%


% Cost function
% pending vectorization
for i =1:m
	J = J + (((theta'*X(i,:)'- y(i))^2)/(2*m));
endfor

% Regularization term
thet_reg=theta(2:end,:);
thet_reg = thet_reg .^2;
regularization_term = (lambda/(2*m))*sum(thet_reg);
J = J + regularization_term;


% Gradients
h = X*theta;
grad = X'*(h-y)/m;

theta_reg = theta;
theta_reg(1,1)=0;
grad = grad + (lambda*theta_reg)/m;




% =========================================================================

grad = grad(:);

end
