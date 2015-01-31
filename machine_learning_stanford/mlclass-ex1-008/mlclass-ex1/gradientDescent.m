function [theta, J_history] = gradientDescent(X, y, theta, alpha, num_iters)
%GRADIENTDESCENT Performs gradient descent to learn theta
%   theta = GRADIENTDESENT(X, y, theta, alpha, num_iters) updates theta by 
%   taking num_iters gradient steps with learning rate alpha

% Initialize some useful values
m = length(y); % number of training examples
J_history = zeros(num_iters, 1);

for iter = 1:num_iters

    % ====================== YOUR CODE HERE ======================
    % Instructions: Perform a single gradient step on the parameter vector
    %               theta. 
    %
    % Hint: While debugging, it can be useful to print out the values
    %       of the cost function (computeCost) and gradient here.
    %
    % theta
    % computeCost(X, y, theta)

    m = length(y);

    % non-vector implementation
    sumatorio_theta0 = 0;
    sumatorio_theta1 = 0;
    

    for i=1:m
        % non-vector implementation
        sumatorio_theta0 = sumatorio_theta0 + (theta(1,1) + theta(2,1)*X(i,2) - y(i,1) ) *X(i,1);
        sumatorio_theta1 = sumatorio_theta1 + (theta(1,1) + theta(2,1)*X(i,2) - y(i,1) ) *X(i,2);

        % vector implementation
       % theta = theta - ((alpha/m)*( ( theta'*X(i,:)' -y(i))*X(i,:)'));
       
    endfor



    % non-vector implementation
    theta(1,1) = theta(1,1) - ((alpha/m)*sumatorio_theta0);
    theta(2,1) = theta(2,1) - ((alpha/m)*sumatorio_theta1);

    % computeCost(X,y,theta)


    % ============================================================

    % Save the cost J in every iteration    
    J_history(iter) = computeCost(X, y, theta);

end

computeCost(X,y,theta)

end
