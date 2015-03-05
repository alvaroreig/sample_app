function [J grad] = nnCostFunction(nn_params, ...
                                   input_layer_size, ...
                                   hidden_layer_size, ...
                                   num_labels, ...
                                   X, y, lambda)
%NNCOSTFUNCTION Implements the neural network cost function for a two layer
%neural network which performs classification
%   [J grad] = NNCOSTFUNCTON(nn_params, hidden_layer_size, num_labels, ...
%   X, y, lambda) computes the cost and gradient of the neural network. The
%   parameters for the neural network are "unrolled" into the vector
%   nn_params and need to be converted back into the weight matrices. 
% 
%   The returned parameter grad should be a "unrolled" vector of the
%   partial derivatives of the neural network.
%

% Reshape nn_params back into the parameters Theta1 and Theta2, the weight matrices
% for our 2 layer neural network
Theta1 = reshape(nn_params(1:hidden_layer_size * (input_layer_size + 1)), ...
                 hidden_layer_size, (input_layer_size + 1));

Theta2 = reshape(nn_params((1 + (hidden_layer_size * (input_layer_size + 1))):end), ...
                 num_labels, (hidden_layer_size + 1));

% Setup some useful variables
m = size(X, 1);
         
% You need to return the following variables correctly 
J = 0;
Theta1_grad = zeros(size(Theta1));
Theta2_grad = zeros(size(Theta2));

% ====================== YOUR CODE HERE ======================
% Instructions: You should complete the code by working through the
%               following parts.
%
% Part 1: Feedforward the neural network and return the cost in the
%         variable J. After implementing Part 1, you can verify that your
%         cost function computation is correct by verifying the cost
%         computed in ex4.m
%
% Part 2: Implement the backpropagation algorithm to compute the gradients
%         Theta1_grad and Theta2_grad. You should return the partial derivatives of
%         the cost function with respect to Theta1 and Theta2 in Theta1_grad and
%         Theta2_grad, respectively. After implementing Part 2, you can check
%         that your implementation is correct by running checkNNGradients
%
%         Note: The vector y passed into the function is a vector of labels
%               containing values from 1..K. You need to map this vector into a 
%               binary vector of 1's and 0's to be used with the neural network
%               cost function.
%
%         Hint: We recommend implementing backpropagation using a for-loop
%               over the training examples if you are implementing it for the 
%               first time.
%
% Part 3: Implement regularization with the cost function and gradients.
%
%         Hint: You can implement this around the code for
%               backpropagation. That is, you can compute the gradients for
%               the regularization separately and then add them to Theta1_grad
%               and Theta2_grad from Part 2.
%


%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% Part one: cost
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

% Expand y into y_for_class binary arrays
y_for_class = zeros(m,num_labels);

for i=1:m
	for j=1:num_labels
		if (y(i) == j)
			y_for_class(i,j) = 1;
		endif
	endfor
endfor

X;
y;
y_for_class;
Theta1;
Theta2;
num_labels;


% Add bias unit to X and get a_1
temp = [ones(size(X, 1), 1) X];
a_1 = temp';
a_1;

% HIDDEN LAYER'
z_2 = Theta1*a_1;
a_2 = sigmoid(z_2);

% insert a_1^(1)=1
a_2 =[ ones(1,size(a_2,2)) ; a_2];

 
% OUTPUT LAYER
z_3 = Theta2*a_2;
a_3 = sigmoid(z_3);

% a_1
% a_2
% a_3
% log_h = log(a_3)
% y_for_class

% non-vector implementation
jsum=0;
for i=1:m
	for k=1:num_labels
		jsum = jsum + ((-1)*y_for_class(i,k))*log(a_3(k,i)) -(1-y_for_class(i,k))*log(1-a_3(k,i));
	endfor
endfor
jsum = (jsum/m);

% vector implementation, doesn't work.
% for i=1:m
%	J = J + sum( ((-1)*y_for_class(i)*log(a_3(i)))-(1-y_for_class(i))*log(1-a_3(i)));
%endfor
% J = J/m;
J = jsum;

% non-vector implementation of regularization
%regularization=0;
%for k=1:size(Theta1,1)
%	for j=2:size(Theta1,2)
%		regularization = regularization + Theta1(k,j)^2;
%	endfor
%endfor

%for k=1:size(Theta2,1)
%	for j=2:size(Theta2,2)
%		regularization = regularization + Theta2(k,j)^2;
%	endfor
%endfor

% J = J +((regularization*lambda)/(2*m));

% vector implementation of regularization

Theta1_without_bias = Theta1(:,2:size(Theta1,2));
Theta2_without_bias = Theta2(:,2:size(Theta2,2));

Theta1_without_bias = Theta1_without_bias.^2;
Theta2_without_bias = Theta2_without_bias.^2;

regularization = sum(sum(Theta1_without_bias)) + sum(sum(Theta2_without_bias));
J = J +((regularization*lambda)/(2*m));


%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% Part two: backpropagation
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

for t=1:m


	%Compute a_3 for this training example

	temp = [ones(size(X(t,:), 1), 1) X(t,:)];
	a_1 = temp';

	
	% HIDDEN LAYER'
	z_2 = Theta1*a_1;
	a_2 = sigmoid(z_2);

	% insert a_1^(1)=1
	a_2 =[ ones(1,size(a_2,2)) ; a_2];

	% OUTPUT LAYER
	z_3 = Theta2*a_2;
	a_3 = sigmoid(z_3);

	% Compute deltas for this training example

	% Delta for output layer

	delta_3= a_3 - y_for_class(t,:)';

	% Delta for hidden layer

	z_2=[1; z_2]; % bias
	delta_2 =(Theta2'*delta_3) .* sigmoidGradient(z_2);
	delta_2 = delta_2(2:end); % remove bias


	% Accumulate gradients
	Theta2_grad = Theta2_grad + delta_3*(a_2');
	Theta1_grad = Theta1_grad + delta_2*(a_1');

endfor

% Divide by m

Theta2_grad = Theta2_grad / m;
Theta1_grad = Theta1_grad / m;

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% Part three: gradient regularization
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

% Replace first row with zeros and then (lambda/m)
Theta2_regularization_term = Theta2;
Theta2_regularization_term = Theta2_regularization_term(:,2:end);
Theta2_regularization_term = [zeros(size(Theta2_regularization_term,1), 1) Theta2_regularization_term];
Theta2_regularization_term = (Theta2_regularization_term *lambda)/m;

Theta1_regularization_term = Theta1;
Theta1_regularization_term = Theta1_regularization_term(:,2:end);
Theta1_regularization_term = [zeros(size(Theta1_regularization_term,1), 1) Theta1_regularization_term];
Theta1_regularization_term = (Theta1_regularization_term *lambda)/m;

% Add the regularizaton terms
Theta1_grad = Theta1_grad + Theta1_regularization_term;
Theta2_grad = Theta2_grad + Theta2_regularization_term;










% -------------------------------------------------------------

% =========================================================================

% Unroll gradients
grad = [Theta1_grad(:) ; Theta2_grad(:)];


end
