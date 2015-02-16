function [J, grad] = costFunctionReg(theta, X, y, lambda)
%COSTFUNCTIONREG Compute cost and gradient for logistic regression with regularization
%   J = COSTFUNCTIONREG(theta, X, y, lambda) computes the cost of using
%   theta as the parameter for regularized logistic regression and the
%   gradient of the cost w.r.t. to the parameters. 

% Initialize some useful values
m = length(y); % number of training examples

% You need to return the following variables correctly 
J = 0;
grad = zeros(size(theta));

% ====================== YOUR CODE HERE ======================
% Instructions: Compute the cost of a particular choice of theta.
%               You should set J to the cost.
%               Compute the partial derivatives and set grad to the partial
%               derivatives of the cost w.r.t. each parameter in theta

sumatorioCosteInicial = 0;

% inefficient implementation, must vectorize it when I have the time
for i=1:m
	sumatorioCosteInicial = sumatorioCosteInicial - y(i)*log(sigmoid(theta'*X(i,:)')) - (1-y(i))*log(1-sigmoid(theta'*X(i,:)'));
endfor

% regularization term
sumatorioRegularizacion = sum (theta.^2) - theta(1)^2;

% inefficent implementation, must vectorize it when I have the time

for j=1:size(theta)
	for i=1:m
		grad(j) = grad(j) + ([sigmoid(theta'*X(i,:)') - y(i)]*X(i,j))/m;
	endfor

	if (j>1)
		grad(j) = grad(j) + (lambda*theta(j))/m;
	endif
endfor

J = sumatorioCosteInicial/m + (lambda*sumatorioRegularizacion)/(2*m);






% =============================================================

end
